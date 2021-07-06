/**
 * 
 */

window.onload = function () {
	var map;
	var mapView;
	const markerImg = "/image/marker-blue.png";
	let markerVectorSource = new ol.source.Vector();
	let markerLayer;
	let markers = [];

	var lon;
	var lat;

	var usernamePage = document.querySelector('#username-page');
	var chatPage = document.querySelector('#chat-page');
	var usernameForm = document.querySelector('#usernameForm');
	var messageForm = document.querySelector('#messageForm');
	var messageInput = document.querySelector('#message');
	var messageArea = document.querySelector('#messageArea');
	var connectingElement = document.querySelector('.connecting');

	var stompClient = null;
	var username = null;

	let colors = ['#2196F3', '#32c787', '#00BCD4', '#ff5652', '#ffc107',
		'#ff85af', '#FF9800', '#39bbb0'];

	function markerSetF() {

	}

	function connect(event) {
		username = document.querySelector('#name').value.trim();

		if (username) {
			usernamePage.classList.add('hidden');
			chatPage.classList.remove('hidden');

			var socket = new SockJS('/ws');
			stompClient = Stomp.over(socket);

			stompClient.connect({}, onConnected, onError);
		}

		event.preventDefault();

		markerSetF();
		initMap();
	}

	function onConnected() {
		stompClient.subscribe('/topic/public', onMessageReceived);
		stompClient.send("/app/chat.addUser", {}, JSON.stringify({
			sender: username + " 위치 :: (" + lat + ", " + lon + ")",
			type: 'JOIN'
		}))

		connectingElement.classList.add('hidden');
	}

	function onError(error) {
		connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
		connectingElement.style.color = 'red';
	}

	function sendMessage(event) {
		const messageContent = messageInput.value.trim();

		if (messageContent && stompClient) {
			const chatMessage = {
				sender: username,
				content: messageInput.value,
				lon: lon,
				lat: lat,
				type: 'CHAT'
			};
			stompClient.send("/app/chat.sendMessage", {}, JSON
				.stringify(chatMessage));
			messageInput.value = '';
		}

		event.preventDefault();
	}

	function onMessageReceived(payload) {
		var message = JSON.parse(payload.body);
		var messageElement = document.createElement('li');

		if (message.type === 'JOIN') {
			messageElement.classList.add('event-message');
			message.content = message.sender + ' joined!';

		} else if (message.type === 'LEAVE') {
			messageElement.classList.add('event-message');
			message.content = message.sender + ' left!';

		} else {
			let today = new Date();
			let year = today.getFullYear(); // 년도
			let month = today.getMonth() + 1;  // 월
			let date = today.getDate();  // 날짜
			let hours = today.getHours(); // 시
			let minutes = today.getMinutes();  // 분
			let currentTimeStr = year + "년 " + month + "월 " + date + "일, " + hours + '시 ' + minutes + '분';

			messageElement.classList.add('chat-message');

			var avatarElement = document.createElement('i');
			var avatarText = document.createTextNode(message.sender[0]);
			avatarElement.appendChild(avatarText);
			avatarElement.style['background-color'] = getAvatarColor(message.sender);
			messageElement.appendChild(avatarElement);

			var usernameElement = document.createElement('span');
			var usernameText = document.createTextNode(message.sender + "( " + currentTimeStr + " )");
			usernameElement.appendChild(usernameText);
			messageElement.appendChild(usernameElement);
		}

		const textElement = document.createElement('p');
		const messageText = document.createTextNode(message.content);
		textElement.appendChild(messageText);

		messageElement.appendChild(textElement);

		messageArea.appendChild(messageElement);
		messageArea.scrollTop = messageArea.scrollHeight;
	}

	function getAvatarColor(messageSender) {
		var hash = 0;
		for (var i = 0; i < messageSender.length; i++) {
			hash = 31 * hash + messageSender.charCodeAt(i);
		}

		var index = Math.abs(hash % colors.length);
		return colors[index];
	}

	usernameForm.addEventListener('submit', connect, true)
	messageForm.addEventListener('submit', sendMessage, true)

	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function (position) {
				lon = position.coords.longitude;
				lat = position.coords.latitude;

			}, function (error) {
				console.error(error);
			}, {
				enableHighAccuracy: false,
				maximumAge: 0,
				timeout: Infinity
			});

		} else {
			alert('GPS를 지원하지 않습니다 .');
		}
	}

	function initMap() {
		var container = document.getElementById('popup');
		var content = document.getElementById('popup-content');
		var closer = document.getElementById('popup-closer');
		var overlay = new ol.Overlay({
			element: container,
			autoPan: true,
			autoPanAnimation: {
				duration: 250,
			},
		});

		let centerPosition = ol.proj.transform([lon, lat], "EPSG:4326", "EPSG:900913");
		mapView = new ol.View({
			zoom: 10,
		});

		closer.onclick = function () {
			overlay.setPosition(undefined);
			closer.blur();
			return false;
		};

		map = new ol.Map({
			layers: [new ol.layer.Tile({
				source: new ol.source.XYZ({
					url: "http://mt.google.com/vt/lyrs=m&x={x}&y={y}&z={z}",
				}),
			}),],
			overlays: [overlay],
			target: "map",
			view: mapView,
		});

		mapView.setCenter(centerPosition);

		markers[0] = new ol.Feature({
			geometry: new ol.geom.Point(0),
		});

		markers[0] = new ol.Feature({
			geometry: new ol.geom.Point(0),
		});

		markers[0].setStyle(new ol.style.Style({
			image: new ol.style.Icon({
				src: markerImg,
			}),
		}));

		markers[0].setGeometry(new ol.geom.Point(ol.proj.transform(
			[lon, lat],
			"EPSG:4326",
			"EPSG:900913"
		)));

		markers[0].lon = lon;
		markers[0].lat = lat;

		markerVectorSource.addFeatures(markers);
		markerLayer = new ol.layer.Vector({
			source: markerVectorSource,
		});

		map.addLayer(markerLayer);
		map.on('singleclick', function (evt) {
			const feature = map.forEachFeatureAtPixel(evt.pixel, function (feature) {
				return feature;
			});
			if (feature) {
				var coordinate = evt.coordinate;
				overlay.setPosition(coordinate);

				var con = "lon :: " + feature.lon + "<br/>lat :: " + feature.lat;
				content.innerHTML = '<p>나의 위치</p><code>' + con + '</code>';
			}
		});
	}

	getLocation();
}
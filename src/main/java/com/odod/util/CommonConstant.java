package com.odod.util;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
abstract public class CommonConstant {

  abstract public static class ResponseUtil {

    public static final String API_RESULT_CODE_KEY = "resultCode";
    public static final Long API_RESULT_CODE_SUCC = 1L;
    public static final Long API_RESULT_CODE_FAIL = 0L;

    public static Map<String, Object> createErrorObj(String statusName, Object statusCode) {
      Map<String, Object> result = new HashMap<>();
      result.put(API_RESULT_CODE_KEY, API_RESULT_CODE_FAIL);
      result.put("statusCode", statusCode);
      result.put("statusMessage", statusName);
      return Collections.unmodifiableMap(result);
    }
  }

  public static final SimpleDateFormat COM_SDF = new SimpleDateFormat("yyyyMMddHHmm");

  public static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
  public static final String FIREBASE_SERVER_KEY = "AAAAykn8vZQ:APA91bEDNOQdDtB3PH9yyUlEjLxVQz9LC9OPM8C3hjRCaIURHIPGIsKmoLYhFWgcP370r_YKRbA90Tx8sOTI_NQ_z0ZE7xfH29_WCHjCevbBH7LADdOW4igC7xOqeF5UtOew9SUs3cH9";

  public static final String REDIS_PREFIX_INFO = "INFO_";
  public static final String REDIS_PREFIX_FLAG = "FLAG_";
  public static final String REDIS_PREFIX_TIME = "TIME_";

  public static final String REDIS_SUFFIX_ALERT = "_ALERT";
  public static final String REDIS_SUFFIX_PM10 = "_PM10";
  public static final String REDIS_SUFFIX_PM25 = "_PM25";
  public static final String REDIS_SUFFIX_CO2 = "_CO2";
  public static final String REDIS_SUFFIX_VOC = "_VOC";
  public static final String REDIS_SUFFIX_TEMP = "_TEMP";
  public static final String REDIS_SUFFIX_HUMI = "_HUMI";

  public static final String REDIS_NO_DATA = "NO_DATA";
  public static final String API_SERVER_HOST_TOTAL = "http://kiotdpd.kweather.co.kr:30101";
  public static final String SEARCH_PATH_SENSOR = "/v1/groups";

  public static final String DEVICE_TYPE_IAQ = "IAQ";
  public static final String DEVICE_TYPE_OAQ = "OAQ";

  public static final int[] PM10_RANGE = {50, 80, 90, 100};
  public static final int[] PM25_RANGE = {50, 80, 90, 100};
  public static final int[] CO2_RANGE = {500, 1000, 1500, 10000};
  public static final int[] VOC_RANGE = {200, 400, 1000, 10000};
  public static final int[] TEMP_IAQ_RANGE = {};
  public static final int[] HUMI_IAQ_RANGE = {};
  public static final int[] TEMP_OAQ_RANGE = {};
  public static final int[] HUMI_OAQ_RANGE = {};

  public static final String[] ELEMENT_TYPE_LIST = {"pm10", "pm25", "co2", "voc", "temp", "humi"};

  public static final String ELEMENT_TYPE_PM10 = "pm10";
  public static final String ELEMENT_TYPE_PM25 = "pm25";
  public static final String ELEMENT_TYPE_CO2 = "co2";
  public static final String ELEMENT_TYPE_VOC = "voc";
  public static final String ELEMENT_TYPE_TEMP = "temp";
  public static final String ELEMENT_TYPE_HUMI = "humi";

  public static final String[][] ELEMENT_PM10_MESSAGE = {
      {"미세먼지 농도 나쁘지 않아요. 잘 유지되고 있어요.", "미세먼지 수치 보통. 적정 수준을 유지하고 있습니다.", "현재 미세먼지 상태 '보통'. 좋습니다!", "미세먼지가 한단계 나빠져 '보통' 상태입니다.", "적정 미세먼지 수치를 유지 중입니다."},
      {"미세먼지 수치가 높아졌어요. 하루 3번 30분씩 충분한 환기를 해주세요. 너무 이른 아침이나 늦은 저녁보다는 낮에 하는 것이 좋아요!", "미세먼지 수치 나쁨! 공기청정기가 필요해 보여요.", "현재 미세먼지 상태 '나쁨' 환기를 하거나 공기청정기를 트는게 어떨까요?", "미세먼지가 두단계 나빠져 '나쁨' 상태입니다.", "미세먼지 수치가 올라가고 있습니다. 산호수,틸란드시아, 벵갈 고무나무등의 공기정화식물을 길러보세요. 실내공기질 개선에 좋습니다."},
      {"미세먼지 수치 매우나쁨! 갑자기 공기질이 나빠졌어요. 원인을 확인하고 해결해보세요.", "미세먼지 수치 '매우나쁨' 분무기로 공중에 물을 뿌려주세요. 공기 중의 미세먼지가 물방울에 흡착되어 떨어집니다. 그 다음 물걸레로 청소해주세요.", "미세먼지가 세단계 나빠져 '매우나쁨' 상태입니다.", "미세먼지가 세단계 나빠져 '매우나쁨' 상태입니다.", "미세먼지가 세단계 나빠져 '매우나쁨' 상태입니다."},
      {"미세먼지 농도가 좋아졌습니다. 아주좋아요!", "미세먼지 수치 좋음. 미세먼지로부터 안전해요!", "현재 미세먼지 상태 '좋음'. 최고에요!", "미세먼지가 한단계 좋아져 '좋음' 상태입니다.", "좋은 미세먼지 상태를 유지 중입니다."},
      {"미세먼지 수치가 높아졌습니다. 공기질을 확인해볼까요?", "미세먼지 수치 나쁨.  물청소를 하면 실내먼지 제거에 효과적입니다. 실외 미세먼지가 나빠도 하루 3번씩 환기시키는것도 잊지마세요.", "현재 미세먼지 상태 '나쁨' 개선이 필요합니다.", "미세먼지가 한단계 나빠져 '나쁨' 상태입니다.", "미세먼지 농도가 높습니다. 물을 충분히 마셔주세요. 기관지의 건조함을 막는 것은 물론 몸속 노폐물 배출에 도움이 됩니다."},
      {"미세먼지 농도가 너무 높아요. 공기청정기가 필요해 보여요.", "미세먼지 수치 매우나쁨. 집안에서의 직화구이는 실내공기질을 크게 나쁘게 합니다. 집안에서는 자제하는게 어떨까요?", "현재 미세먼지 상태 '매우나쁨'. 주의가 필요합니다.", "미세먼지가 두단계 높아져 '매우나쁨' 상태입니다.", "미세먼지 농도가 아주 높습니다. 빠른 해결이 필요합니다."},
      {"미세먼지 수치가 낮아졌어요. 아주 좋습니다!", "미세먼지 수치 좋음. 이대로만 유지해봐요", "현재 미세먼지 상태 '좋음'. 미세먼지로부터 안전해요", "미세먼지가 두단계 낮아져 '좋음' 상태입니다.", "미세먼지 수치가 많이 떨어졌습니다. 아주 좋아요."},
      {"미세먼지 수치가 낮아져 보통 수준입니다. 조금만 더 개선해볼까요?", "미세먼지 수치 보통. 적정 수준입니다.", "현재 미세먼지 상태 '보통' 나쁘지 않아요.", "미세먼지가 한단계 낮아져 '보통' 상태입니다.", "미세먼지 수치가 떨어지고 있습니다. 조금 더 노력해봐요."},
      {"미세먼지 수치가 오르고 있습니다. 산호수,틸란드시아, 벵갈 고무나무등의 공기정화식물을 길러보세요. 실내공기질 개선에 좋습니다.", "미세먼지 수치 '매우나쁨' 공기청정기 등 환기시스템 필터는 점검 하셨나요? 먼지 제거 기능 저하로 실내공기 질을 적정 수준으로 유지할 수 없거나 오히려 세균 오염으로 악화될 수 있습니다. ", "미세먼지 수치 '매우나쁨' 빠른 개선이 필요한 상태에요.", "미세먼지가 한단계 높아져 '매우나쁨' 상태입니다. 빠른 해결이 필요합니다.", "미세먼지 수치가 오르고 있습니다. 오늘 저녁 메뉴는 고등어 어때요? 고등어에는 오메가-3 지방산이 풍부해요. 오메가-3는 기도의 염증을 완화해 폐 질환의 증상인 호흡곤란을 개선하는 효과가 있어요."},
      {"미세먼지 수치가 낮아졌어요. 이대로 유지해봐요!", "미세먼지 수치 '좋음' 오늘은 창문을 활짝 열고 청소를 해볼까요?", "미세먼지가 세단계 낮아져 '좋음' 상태입니다.", "미세먼지가 세단계 낮아져 '좋음' 상태입니다.", "미세먼지가 세단계 낮아져 '좋음' 상태입니다."},
      {"미세먼지 수치가 낮아지고 있습니다. 좀 더 노력해볼까요?", "미세먼지 수치 '보통' 좋아요!", "현재 미세먼지 수치 '보통' 많이 좋아졌어요.", "미세먼지가 두단계 낮아져 '보통' 상태입니다.", "미세먼지 수치가 낮아지고 있습니다. 이대로 유지해봐요."},
      {"미세먼지 수치가 조금 낮아져 '나쁨' 상태입니다. 하루 3번 30분씩 충분한 환기를 해주세요. 너무 이른 아침이나 늦은 저녁에는 오염물질이 대기 중에 정체되어 있을 수 있기 때문에 낮에 하는 것이 좋아요!", "미세먼지 수치 '나쁨' 미세먼지에 좋은 배,블루베리,귤,사과 등의 과일을 충분히 섭취해보세요.", "현재 미세먼지 수치 '나쁨' 개선이 필요합니다.", "미세먼지가 한단계 낮아져 '나쁨' 상태입니다.", "미세먼지 '나쁨' 오늘은 탄산음료 대신 미세먼지에 좋은 도라지차,대추차,오미자차를 마셔보는건 어떨까요?"},
    };
  public static final String[][] ELEMENT_PM25_MESSAGE = {
      {"좋음 => 보통, type A", "좋음 => 보통, type B", "좋음 => 보통, type C", "좋음 => 보통, type D", "좋음 => 보통, type E"},
      {"좋음 => 나쁨, type A", "좋음 => 나쁨, type B", "좋음 => 나쁨, type C", "좋음 => 나쁨, type D", "좋음 => 나쁨, type E"},
      {"좋음 => 매우 나쁨, type A", "좋음 => 매우 나쁨, type B", "좋음 => 매우 나쁨, type C", "좋음 => 매우 나쁨, type D", "좋음 => 매우 나쁨, type E"},
      {"보통 => 좋음, type A", "보통 => 좋음, type B", "보통 => 좋음, type C", "보통 => 좋음, type D", "보통 => 좋음, type E"},
      {"보통 => 나쁨, type A", "보통 => 나쁨, type B", "보통 => 나쁨, type C", "보통 => 나쁨, type D", "보통 => 나쁨, type E"},
      {"보통 => 매우 나쁨, type A", "보통 => 매우 나쁨, type B", "보통 => 매우 나쁨, type C", "보통 => 매우 나쁨, type D", "보통 => 매우 나쁨, type E"},
      {"나쁨 => 좋음, type A", "나쁨 => 좋음, type B", "나쁨 => 좋음, type C", "나쁨 => 좋음, type D", "나쁨 => 좋음, type E"},
      {"나쁨 => 보통, type A", "나쁨 => 보통, type B", "나쁨 => 보통, type C", "나쁨 => 보통, type D", "나쁨 => 보통, type E"},
      {"나쁨 => 매우 나쁨, type A", "나쁨 => 매우 나쁨, type B", "나쁨 => 매우 나쁨, type C", "나쁨 => 매우 나쁨, type D", "나쁨 => 매우 나쁨, type E"},
      {"매우 나쁨 => 좋음, type A", "매우 나쁨 => 좋음, type B", "매우 나쁨 => 좋음, type C", "매우 나쁨 => 좋음, type D", "매우 나쁨 => 좋음, type E"},
      {"매우 나쁨 => 보통, type A", "매우 나쁨 => 보통, type B", "매우 나쁨 => 보통, type C", "매우 나쁨 => 보통, type D", "매우 나쁨 => 보통, type E"},
      {"매우 나쁨 => 나쁨, type A", "매우 나쁨 => 나쁨, type B", "매우 나쁨 => 나쁨, type C", "매우 나쁨 => 나쁨, type D", "매우 나쁨 => 나쁨, type E"},
    };
  public static final String[][] ELEMENT_CO2_MESSAGE = {
      {"좋음 => 보통, type A", "좋음 => 보통, type B", "좋음 => 보통, type C", "좋음 => 보통, type D", "좋음 => 보통, type E"},
      {"좋음 => 나쁨, type A", "좋음 => 나쁨, type B", "좋음 => 나쁨, type C", "좋음 => 나쁨, type D", "좋음 => 나쁨, type E"},
      {"좋음 => 매우 나쁨, type A", "좋음 => 매우 나쁨, type B", "좋음 => 매우 나쁨, type C", "좋음 => 매우 나쁨, type D", "좋음 => 매우 나쁨, type E"},
      {"보통 => 좋음, type A", "보통 => 좋음, type B", "보통 => 좋음, type C", "보통 => 좋음, type D", "보통 => 좋음, type E"},
      {"보통 => 나쁨, type A", "보통 => 나쁨, type B", "보통 => 나쁨, type C", "보통 => 나쁨, type D", "보통 => 나쁨, type E"},
      {"보통 => 매우 나쁨, type A", "보통 => 매우 나쁨, type B", "보통 => 매우 나쁨, type C", "보통 => 매우 나쁨, type D", "보통 => 매우 나쁨, type E"},
      {"나쁨 => 좋음, type A", "나쁨 => 좋음, type B", "나쁨 => 좋음, type C", "나쁨 => 좋음, type D", "나쁨 => 좋음, type E"},
      {"나쁨 => 보통, type A", "나쁨 => 보통, type B", "나쁨 => 보통, type C", "나쁨 => 보통, type D", "나쁨 => 보통, type E"},
      {"나쁨 => 매우 나쁨, type A", "나쁨 => 매우 나쁨, type B", "나쁨 => 매우 나쁨, type C", "나쁨 => 매우 나쁨, type D", "나쁨 => 매우 나쁨, type E"},
      {"매우 나쁨 => 좋음, type A", "매우 나쁨 => 좋음, type B", "매우 나쁨 => 좋음, type C", "매우 나쁨 => 좋음, type D", "매우 나쁨 => 좋음, type E"},
      {"매우 나쁨 => 보통, type A", "매우 나쁨 => 보통, type B", "매우 나쁨 => 보통, type C", "매우 나쁨 => 보통, type D", "매우 나쁨 => 보통, type E"},
      {"매우 나쁨 => 나쁨, type A", "매우 나쁨 => 나쁨, type B", "매우 나쁨 => 나쁨, type C", "매우 나쁨 => 나쁨, type D", "매우 나쁨 => 나쁨, type E"},
    };
  public static final String[][] ELEMENT_VOC_MESSAGE = {
      {"좋음 => 보통, type A", "좋음 => 보통, type B", "좋음 => 보통, type C", "좋음 => 보통, type D", "좋음 => 보통, type E"},
      {"좋음 => 나쁨, type A", "좋음 => 나쁨, type B", "좋음 => 나쁨, type C", "좋음 => 나쁨, type D", "좋음 => 나쁨, type E"},
      {"좋음 => 매우 나쁨, type A", "좋음 => 매우 나쁨, type B", "좋음 => 매우 나쁨, type C", "좋음 => 매우 나쁨, type D", "좋음 => 매우 나쁨, type E"},
      {"보통 => 좋음, type A", "보통 => 좋음, type B", "보통 => 좋음, type C", "보통 => 좋음, type D", "보통 => 좋음, type E"},
      {"보통 => 나쁨, type A", "보통 => 나쁨, type B", "보통 => 나쁨, type C", "보통 => 나쁨, type D", "보통 => 나쁨, type E"},
      {"보통 => 매우 나쁨, type A", "보통 => 매우 나쁨, type B", "보통 => 매우 나쁨, type C", "보통 => 매우 나쁨, type D", "보통 => 매우 나쁨, type E"},
      {"나쁨 => 좋음, type A", "나쁨 => 좋음, type B", "나쁨 => 좋음, type C", "나쁨 => 좋음, type D", "나쁨 => 좋음, type E"},
      {"나쁨 => 보통, type A", "나쁨 => 보통, type B", "나쁨 => 보통, type C", "나쁨 => 보통, type D", "나쁨 => 보통, type E"},
      {"나쁨 => 매우 나쁨, type A", "나쁨 => 매우 나쁨, type B", "나쁨 => 매우 나쁨, type C", "나쁨 => 매우 나쁨, type D", "나쁨 => 매우 나쁨, type E"},
      {"매우 나쁨 => 좋음, type A", "매우 나쁨 => 좋음, type B", "매우 나쁨 => 좋음, type C", "매우 나쁨 => 좋음, type D", "매우 나쁨 => 좋음, type E"},
      {"매우 나쁨 => 보통, type A", "매우 나쁨 => 보통, type B", "매우 나쁨 => 보통, type C", "매우 나쁨 => 보통, type D", "매우 나쁨 => 보통, type E"},
      {"매우 나쁨 => 나쁨, type A", "매우 나쁨 => 나쁨, type B", "매우 나쁨 => 나쁨, type C", "매우 나쁨 => 나쁨, type D", "매우 나쁨 => 나쁨, type E"},
    };
}

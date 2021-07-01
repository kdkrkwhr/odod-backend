package com.odod.ws;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {

  private MessageType type;
  private String content;
  private String sender;

}

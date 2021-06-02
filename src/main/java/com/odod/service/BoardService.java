package com.odod.service;

import org.springframework.stereotype.Service;
import com.odod.board.Board;
import com.odod.board.BoardRepository;
import com.odod.dto.BoardRequestDto;

@Service
public class BoardService {

  private BoardRepository repository;

  public int save(BoardRequestDto board) {
    repository.save(Board.builder().email(board.getEmail()).subject(board.getSubject())
        .content(board.getContent()).filePath(board.getFilePath()).lon(board.getLon())
        .lat(board.getLat()).build());
    return 1;
  }
}

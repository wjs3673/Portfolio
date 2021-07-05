package com.project.four.model.dto;

public class BoardDto {

    private String board_id;
    private String gone_id;
    private String user_id;
    private String title;
    private String writer;
    private String content;
    private String board_dt;
    private int count;
    private int secret;
    private int flag;
    private String name;

    public BoardDto() {
        super();
    }

public BoardDto(String board_id, String gone_id, String title, String writer, String content, String board_dt,
        int count, int secret, int flag) {
    super();
    this.board_id = board_id;
    this.gone_id = gone_id;
    this.title = title;
    this.writer = writer;
    this.content = content;
    this.board_dt = board_dt;
    this.count = count;
    this.secret = secret;
    this.flag = flag;
}

public BoardDto(String board_id, String gone_id, String user_id, String title, String writer, String content,
        String board_dt, int count, int secret, int flag) {
    super();
    this.board_id = board_id;
    this.gone_id = gone_id;
    this.user_id = user_id;
    this.title = title;
    this.writer = writer;
    this.content = content;
    this.board_dt = board_dt;
    this.count = count;
    this.secret = secret;
    this.flag = flag;
}

public String getBoard_id() {
    return board_id;
}

public void setBoard_id(String board_id) {
    this.board_id = board_id;
}

public String getGone_id() {
    return gone_id;
}

public void setGone_id(String gone_id) {
    this.gone_id = gone_id;
}

public String getUser_id() {
    return user_id;
}

public void setUser_id(String user_id) {
    this.user_id = user_id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getWriter() {
    return writer;
}

public void setWriter(String writer) {
    this.writer = writer;
}

public String getContent() {
    return content;
}

public void setContent(String content) {
    this.content = content;
}

public String getBoard_dt() {
    return board_dt;
}

public void setBoard_dt(String board_dt) {
    this.board_dt = board_dt;
}

public int getCount() {
    return count;
}

public void setCount(int count) {
    this.count = count;
}

public int getSecret() {
    return secret;
}

public void setSecret(int secret) {
    this.secret = secret;
}

public int getFlag() {
    return flag;
}

public void setFlag(int flag) {
    this.flag = flag;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

@Override
public String toString() {
    return "BoardDto [board_id=" + board_id + ", gone_id=" + gone_id + ", title=" + title + ", writer=" + writer
            + ", content=" + content + ", board_dt=" + board_dt + ", count=" + count + ", secret=" + secret
            + ", flag=" + flag + "]";
}
}
package com.hikarukimi.taimountain.entity;


import java.time.LocalDateTime;
/**
 * @author Hikarukimi
 */
public class WeatherWarning {

    // 缩写（如 ["大风", "蓝色预警"]）
    private String[] abbreviation;

    // 事件类别（如 "gale"）
    private String className;

    // 创建时间
    private LocalDateTime created;

    // 数据来源（如 "武安市气象台"）
    private String credit;

    // 详细索引
    private String detailIndex;

    // 预警结束时间
    private LocalDateTime end;

    // 事件名称（如 "大风"）
    private String event;

    // 事件英文名称（如 "gale"）
    private String eventName;

    // 唯一标识符
    private String id;

    // 预警级别（如 "Blue"）
    private String level;

    // 安全指南
    private String safetyGuide;

    // 预警严重性描述（如 "蓝色预警"）
    private String severity;

    // 简短描述（如 "大风预警"）
    private String shortCap;

    // 重要性标识（如 "B"）
    private String significance;

    // 预警开始时间
    private LocalDateTime start;

    // 标题（如 "大风 - 蓝色预警"）
    private String title;

    // Getter 和 Setter 方法
    public String[] getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String[] abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDetailIndex() {
        return detailIndex;
    }

    public void setDetailIndex(String detailIndex) {
        this.detailIndex = detailIndex;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSafetyGuide() {
        return safetyGuide;
    }

    public void setSafetyGuide(String safetyGuide) {
        this.safetyGuide = safetyGuide;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getShortCap() {
        return shortCap;
    }

    public void setShortCap(String shortCap) {
        this.shortCap = shortCap;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "WeatherWarning{" +
                "abbreviation=" + String.join(", ", abbreviation) +
                ", className='" + className + '\'' +
                ", created=" + created +
                ", credit='" + credit + '\'' +
                ", detailIndex='" + detailIndex + '\'' +
                ", end=" + end +
                ", event='" + event + '\'' +
                ", eventName='" + eventName + '\'' +
                ", id='" + id + '\'' +
                ", level='" + level + '\'' +
                ", safetyGuide='" + safetyGuide + '\'' +
                ", severity='" + severity + '\'' +
                ", shortCap='" + shortCap + '\'' +
                ", significance='" + significance + '\'' +
                ", start=" + start +
                ", title='" + title + '\'' +
                '}';
    }
}

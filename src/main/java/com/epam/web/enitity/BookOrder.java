package com.epam.web.enitity;

import java.sql.Date;

public class BookOrder implements Identifiable {

    private long id;

    private long userId;
    private long bookId;

    private BookOrderStatus orderStatus;

    private Date startDate;
    private Date endDate;

    public BookOrder(long id, long userId, long bookId, BookOrderStatus orderStatus, Date startDate, Date endDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.orderStatus = orderStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public BookOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(BookOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

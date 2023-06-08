package com.finance.app.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class ReportEntity {
    @ManyToOne
    private Category category;
    @ManyToOne
    private Transaction transaction;
    @Column(name = "amount")
    private BigDecimal amount;

}

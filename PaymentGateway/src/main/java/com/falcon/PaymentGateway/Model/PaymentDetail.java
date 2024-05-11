package com.falcon.PaymentGateway.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "payments")
public class PaymentDetail {
    @Id
    @Generated
    public int id;
    public double amount;
    public String paymentSecreatKey;
    public String currency;
    public int systemUserId;
    public int paymentStatus;
}

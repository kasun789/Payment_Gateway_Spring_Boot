package com.falcon.PaymentGateway.Repository;

import com.falcon.PaymentGateway.Model.PaymentDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentDetail,Integer> {

}

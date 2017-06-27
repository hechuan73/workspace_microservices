package inside_payment.init;

import inside_payment.domain.CreateAccountInfo;
import inside_payment.domain.Payment;
import inside_payment.domain.PaymentInfo;
import inside_payment.repository.PaymentRepository;
import inside_payment.service.InsidePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/21.
 */
@Component
public class InitData implements CommandLineRunner {
    @Autowired
    InsidePaymentService service;

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception{
        CreateAccountInfo info1 = new CreateAccountInfo();
        info1.setUserId("4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f");
        info1.setBalance("10000");
        service.createAccount(info1);

//        PaymentInfo info2 = new PaymentInfo();
//        info2.setOrderId("5ad7750b-a68b-49c0-a8c0-32776b067703");
//        info2.setTripId("G1234");
//        info2.setUserId("4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f");
//        service.pay(info2);
        Payment payment = new Payment();
        payment.setOrderId("5ad7750b-a68b-49c0-a8c0-32776b067702");
        payment.setPrice("100.0");
        payment.setUserId("4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f");
        paymentRepository.save(payment);
    }
}


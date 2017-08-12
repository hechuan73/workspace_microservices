package reproduction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reproduction.async.AsyncTask;
import reproduction.domain.Information;
import reproduction.domain.Order;
import reproduction.domain.OrderTicketsInfo;
import reproduction.domain.OrderTicketsResult;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/8/7.
 */
@Service
public class ReproductionServiceImpl implements ReproductionService{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AsyncTask asyncTask;

    @Override
    public OrderTicketsResult reproduct(OrderTicketsInfo info, String loginId, String loginToken) throws InterruptedException, ExecutionException, TimeoutException {
        Future<OrderTicketsResult> preserve1 = asyncTask.preserve(info, loginId, loginToken);
        Thread.sleep(5000);
        Date startTime = new Date();
        Future<OrderTicketsResult> preserve2 = asyncTask.preserve(info, loginId, loginToken);
        OrderTicketsResult result = preserve2.get();
        Date endTime = new Date();

        if(!checkTime(startTime, endTime)){
            throw new TimeoutException();
        }

        return result;
//        Thread.sleep(15000);
//        Future<Boolean> payTask = asyncTask.pay(loginId, loginToken);
//        payTask.get(2000, TimeUnit.MILLISECONDS);
//        return preserve2.get();
    }

    @Override
    public OrderTicketsResult reproductCorrect(OrderTicketsInfo info, String loginId, String loginToken) throws InterruptedException, ExecutionException, TimeoutException {

//        Thread.sleep(100000);
//        Future<Boolean> payTask = asyncTask.pay(loginId, loginToken);
//        payTask.get(2000, TimeUnit.MILLISECONDS);
        Future<OrderTicketsResult> preserve1 = asyncTask.preserve(info, loginId, loginToken);
        Thread.sleep(10000);
        Date startTime = new Date();
        Future<OrderTicketsResult> preserve2 = asyncTask.preserve(info, loginId, loginToken);
        OrderTicketsResult result = preserve2.get();
        Date endTime = new Date();

        if(!checkTime(startTime, endTime)){
            throw new TimeoutException();
        }

        return result;
    }

    @Override
    public OrderTicketsResult reproductOther(OrderTicketsInfo info, String loginId, String loginToken) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public OrderTicketsResult reproductOtherCorrect(OrderTicketsInfo info, String loginId, String loginToken) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    private boolean checkTime(Date startTime, Date endTime) {

        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(startTime);
        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(endTime);

        if(calDateA.get(Calendar.HOUR_OF_DAY) == calDateB.get(Calendar.HOUR_OF_DAY)){
            if(  calDateB.get(Calendar.MINUTE) - calDateA.get(Calendar.MINUTE) == 1){
                if(calDateB.get(Calendar.SECOND) - calDateA.get(Calendar.SECOND) < 40){
                    return true;
                }else{
                    return false;
                }

            }else{
                return false;
            }
        }else{
            return false;
        }

    }
}

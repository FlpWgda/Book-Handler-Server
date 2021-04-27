package io.swagger.aspects;

import io.swagger.model.Publication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@RestController
public class BookAspect {

    static Publication bestRatedPublication= null;
    static Map<String,Integer> statistics= new HashMap<String,Integer>();

    @Pointcut("execution(* io.swagger.api.PublicationApiController.findAllPublications())")
    public void pointcutGetAllPublications(){}

    @Pointcut("execution(* io.swagger.api.PublicationApiController.*(..))")
    public void pointcutPublicationApi(){}

    @AfterReturning(pointcut = "pointcutGetAllPublications()", returning="responseEntity")
    public void setBestRatedBook(JoinPoint joinPoint, ResponseEntity<List<Publication>> responseEntity){
        for(Publication p: responseEntity.getBody()){
            if(bestRatedPublication == null || bestRatedPublication.getRating()<p.getRating()){
                bestRatedPublication = p;
            }
        }
    }
    @Before("pointcutPublicationApi()")
    public void updateCounters(JoinPoint joinPoint){
        String joinpointSubstring = joinPoint.getSignature().toString().substring(30);
        System.out.println(joinpointSubstring);
        if(statistics.containsKey(joinpointSubstring)){
            statistics.put(joinpointSubstring,
                    statistics.get(joinpointSubstring)+1);
        }
        else{
            statistics.put(joinpointSubstring,1);
        }

    }

    @GetMapping("/getBestRated")
    public ResponseEntity<Publication> getBestRatedBook(){
        return new ResponseEntity<Publication>(bestRatedPublication,HttpStatus.OK);
    }
    @GetMapping("/getPublicationApiUsageStats")
    public ResponseEntity<Map<String,String>> getMethodUsageStats(){
        int counter = 0;
        for(Map.Entry<String, Integer> entry: statistics.entrySet()){
            counter += entry.getValue();
        }
        Map<String, String> stat = new HashMap<String,String>();
        for(Map.Entry<String, Integer> entry: statistics.entrySet()){
            stat.put(entry.getKey(),((int)(100 * ((float)entry.getValue()/counter))+ "%"));
        }
        return new ResponseEntity<Map<String,String>>(stat,HttpStatus.OK);
    }

    /*class Statistic{

        Map map = new HashMap<String,Integer>();

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }
    }*/
}

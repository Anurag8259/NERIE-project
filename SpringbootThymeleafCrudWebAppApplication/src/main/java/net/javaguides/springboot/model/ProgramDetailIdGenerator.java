package net.javaguides.springboot.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.time.Year;
import java.util.Random;
import java.util.UUID;

public class ProgramDetailIdGenerator implements IdentifierGenerator {


//    private static final long serialVersionUID=-160234233432342342L;
    int year= Year.now().getValue();
    Random rand=new Random();
    @Override
    public Object generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        return "NER_"+year+"-"+(year-1)+"-"+ rand.nextInt(100000);
    }
}

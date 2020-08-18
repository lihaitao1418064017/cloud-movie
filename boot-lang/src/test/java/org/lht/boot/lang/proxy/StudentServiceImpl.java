package org.lht.boot.lang.proxy;

public class StudentServiceImpl implements IStudentService {


    @Override
    public void pay() {
        System.out.println("交学费");
    }

    @Override
    public void attendClass() {
        //先交学费
        pay();

        System.out.println("开始上学");
    }
}
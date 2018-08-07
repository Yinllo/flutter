package com.desjf.dsjr.bean;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/30 0030
 * @Describe
 */

public class TestBean {


    /**
     * total : 2
     * users : [{"naem":"小明","age":"23","sex":"男"},{"naem":"小红","age":"20","sex":"女"}]
     */

    private String total;
    private List<UsersBean> users;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * naem : 小明
         * age : 23
         * sex : 男
         */

        private String naem;
        private String age;
        private String sex;

        public String getNaem() {
            return naem;
        }

        public void setNaem(String naem) {
            this.naem = naem;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}

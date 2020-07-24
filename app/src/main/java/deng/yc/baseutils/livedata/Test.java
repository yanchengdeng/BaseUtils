package deng.yc.baseutils.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import deng.yc.baseutils.databind.User;

/**
 * @Author : yancheng
 * @Date : 2020/7/23
 * @Time : 13:55
 * @Describe ：
 **/
class Test {

    class NameTips{
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public static void main(String[] args) {
        LiveData<NameTips> users = new LiveData<NameTips>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super NameTips> observer) {
                super.observe(owner, observer);
            }
        };
      LiveData<String> stringLiveData =   Transformations.map(users, user -> user.getFirstName() + user.getLastName());
    }
}

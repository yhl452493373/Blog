package com.yang.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    public static SystemConfig config;

    private UsernameLength usernameLength;
    private PasswordLength passwordLength;
    private Salt salt;

    public static UsernameLength getUsernameLength() {
        return config.usernameLength;
    }

    public void setUsernameLength(UsernameLength usernameLength) {
        this.usernameLength = usernameLength;
    }

    public static PasswordLength getPasswordLength() {
        return config.passwordLength;
    }

    public void setPasswordLength(PasswordLength passwordLength) {
        this.passwordLength = passwordLength;
    }

    public static Salt getSalt() {
        return config.salt;
    }

    public void setSalt(Salt salt) {
        this.salt = salt;
    }

    @PostConstruct
    public void init() {
        config = this;
    }

    public static class UsernameLength {
        private Integer min;
        private Integer max;

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }
    }

    public static class PasswordLength {
        private Integer min;
        private Integer max;

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }
    }

    public static class Salt {
        private Integer size;
        private Integer hashCount;

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getHashCount() {
            return hashCount;
        }

        public void setHashCount(Integer hashCount) {
            this.hashCount = hashCount;
        }
    }
}

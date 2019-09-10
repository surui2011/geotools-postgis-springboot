package com.example.postgis_spring.module;

import lombok.AllArgsConstructor;

/**
 * @ClassName: Command
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@AllArgsConstructor
public class Function {
    public String function;

    public String toString() {
        return this.function;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Users;

/**
 *
 * @author thepoet
 */
public interface IUsers extends IService<Users,Integer>{
    Users findByStatus(int status);
    Users findByMail(String email);
}

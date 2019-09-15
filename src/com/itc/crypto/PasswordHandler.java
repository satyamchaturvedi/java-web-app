
package com.itc.crypto;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class PasswordHandler {

   public  String encrypt(String password) {
      String encrPswd = null;
      byte[] keyValue = new byte[] { 'T', 'o', 'p', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y', '1', '2', '3', '4' };
      Key key = new SecretKeySpec(keyValue, "AES");
      try {
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(Cipher.ENCRYPT_MODE, key);
         byte[] arr = cipher.doFinal(password.getBytes());
         encrPswd = new BASE64Encoder().encode(arr);
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      } catch (NoSuchPaddingException e) {
         e.printStackTrace();
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (IllegalBlockSizeException e) {
         e.printStackTrace();
      } catch (BadPaddingException e) {
         e.printStackTrace();
      }
      return encrPswd;
   }

   public  String decrypt(String encrPassword) {
      String decrPswd = null;
      byte[] keyValue = new byte[] { 'T', 'o', 'p', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y', '1', '2', '3', '4' };
      Key key = new SecretKeySpec(keyValue, "AES");
      try {
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(Cipher.DECRYPT_MODE, key);
         byte[] decr = new BASE64Decoder().decodeBuffer(encrPassword);
         byte[] decrVal = cipher.doFinal(decr);
         decrPswd = new String(decrVal);
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      } catch (NoSuchPaddingException e) {
         e.printStackTrace();
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (IllegalBlockSizeException e) {
         e.printStackTrace();
      } catch (BadPaddingException e) {
         e.printStackTrace();
      }

      return decrPswd;
   }
   }

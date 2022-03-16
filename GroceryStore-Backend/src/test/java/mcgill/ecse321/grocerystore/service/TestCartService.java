package mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Person;

public class TestCartService {
   
   @Mock
   private CartRepository cartDao;
   @Mock
   private PersonRepository personDao;
   @Mock
   private AccountRepository accountDao;

   @InjectMocks
   private CartService service;
   @InjectMocks
   private PersonService personService;
   @InjectMocks
   private AccountService accountService;

   private static final int ID = 12345;
   private static final Date date = java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 31));;
   Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
   Account account = accountService.createAccount("username123", "password123", false, 123, person);

   @BeforeEach
   public void setMockOutput() {
       lenient().when(cartDao.findCartById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
           if (invocation.getArgument(0).equals(ID)) {
               Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
			   Account account = accountService.createAccount("username123", "password123", false, 123, person);
               Cart cart = new Cart();
               cart.setAccount(account);
               cart.setDate(date);
               return cart;
           } 
           else {
               return null;
           }
   });
   Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
       return invocation.getArgument(0);
   };
   lenient().when(cartDao.save(any(Cart.class))).thenAnswer(returnParameterAsAnswer);
   }

   
   @Test
   public void testCreateCart() {  
       Cart cart = null;
       try {
           cart = service.createCart(date,account);
       } catch (IllegalArgumentException e) {
           fail();
       }
       assertNotNull(cart);
       assertEquals(account,cart.getAccount());
       assertEquals(date, cart.getDate());
   }

   @Test
   public void testCreateCartbyEmptyAccount() {  
       Account account1=null;
       Cart cart = null;
       String error = "";
       try {
           cart = service.createCart(date,account1);
       } catch (IllegalArgumentException e) {
           error = e.getMessage();
       }
       assertNull(cart);
       assertEquals(error,"Cart's corresponding account cannot be empty");
   }

   @Test
   public void testCreateCartbyEmptyDate() {  
       Date date1=null;
       Cart cart = null;
       String error = "";
       try {
           cart = service.createCart(date1,account);
       } catch (IllegalArgumentException e) {
           error = e.getMessage();
       }
       assertNull(cart);
       assertEquals(error,"Cart Date cannot be empty");
   }

   @Test
   public void testGetCartById() {
       Cart cart = null;
       try {
           cart = service.getCart(ID);
       } catch (IllegalArgumentException e) {
           fail();
       }
       assertNotNull(cart);
       assertEquals(ID,cart.getId());
   }

   @Test
   public void testGetCartByIdNegative() {
       Cart cart = null;
       String error = "";
       int id = -1;
       try {
           cart = service.getCart(id);
       } catch (IllegalArgumentException e) {
           error = e.getMessage();
       }
       assertNull(cart);
       assertEquals(error,"The id cannot be a negative number");
   }

   @Test
   public void testGetCartByIdNull() {
       Cart cart = null;
       String error = "";
       try {
           cart = service.getCart(ID);
       } catch (IllegalArgumentException e) {
           error = e.getMessage();
       }
       assertNull(cart);
       assertEquals(error,"No cart with id " + ID + " exists");
   }

   @Test
   public void testGetCartByEmptyDate() {
       List<Cart> cart = new ArrayList<Cart>();
       String error = "";
       Date date1=null;
       try {
           cart = service.getCartbyDate(date1);
       } catch (IllegalArgumentException e) {
           error = e.getMessage();
       }
       assertEquals(cart.size(),0);
       assertEquals(error,"The date cannot be empty");
   }

   @Test
   public void testupdateCart() {
       Cart cart=null;
       try {
           cart = service.updateCart(ID,date,account);
       } catch (IllegalArgumentException e) {
           fail();
       }
       assertNotNull(cart);
       assertEquals(ID,cart.getId());
       assertEquals(date,cart.getDate());
   }
   
   @Test
   public void testupdateCartWithNegativeID() {
       Cart cart=null;
       int id=-1;
       String error="";
       try {
           cart = service.updateCart(id,date,account);
       } catch (IllegalArgumentException e) {
           fail();
       }
       assertNull(cart);
       assertEquals(error,"Id cannot be negative");
   }

   @Test
   public void testupdateCartWithEmptyID() {
       Cart cart=null;
       int id=0;
       String error="";
       try {
           cart = service.updateCart(id,date,account);
       } catch (IllegalArgumentException e) {
           fail();
       }
       assertNull(cart);
       assertEquals(error,"Id cannot be empty");
   }

   @Test
   public void testupdateCartWithEmptyDate() {
       Cart cart=null;
       Date date1=null;
       String error="";
       try {
           cart = service.updateCart(ID,date1,account);
       } catch (IllegalArgumentException e) {
           fail();
       }
       assertNull(cart);
       assertEquals(error,"Date cannot be empty");
   }

   @Test
   public void testupdateCartWithEmptyAccount() {
       Account account1=null;
       Cart cart=null;
       String error="";
       try {
           cart = service.updateCart(ID,date,account1);
       } catch (IllegalArgumentException e) {
           fail();
       }
       assertNull(cart);
       assertEquals(error,"Account cannot be empty");
   }

   @Test
   public void testdeleteCartbyId() {
       boolean cartdeleted=false;
       try{
           cartdeleted=service.deleteCartbyID(ID);
       }catch(IllegalArgumentException e){
           fail();
       }
       assertTrue(cartdeleted);
   }

   @Test
   public void testdeleteCartbyNegativeId() {
       boolean cartdeleted=false;
       try{
           cartdeleted=service.deleteCartbyID(-1);
       }catch(IllegalArgumentException e){
           fail();
       }
       assertTrue(cartdeleted);
   }

   @Test
   public void testdeleteCartbyEmptyId() {
       boolean cartdeleted=false;
       try{
           cartdeleted=service.deleteCartbyID(0);
       }catch(IllegalArgumentException e){
           fail();
       }
       assertTrue(cartdeleted);
   }

   @Test
   public void testdeleteCartbyDate() {
       boolean cartdeleted=false;
       try{
           cartdeleted=service.deleteCartbyDate(date);
       }catch(IllegalArgumentException e){
           fail();
       }
       assertTrue(cartdeleted);
   }

   @Test
   public void testdeleteCartbyEmptyDate() {
       boolean cartdeleted=false;
       Date date1=null;
       try{
           cartdeleted=service.deleteCartbyDate(date1);
       }catch(IllegalArgumentException e){
           fail();
       }
       assertTrue(cartdeleted);
   }
}
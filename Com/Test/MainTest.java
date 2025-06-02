package Com.Test;

import Com.Controller.CustomerDAO;
import Com.Model.Model_Customer;

import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();

        Model_Customer customer = new Model_Customer();
        customer.setName("Nguyễn Văn A");
        customer.setGender("Nam");
        customer.setBirthDate(LocalDate.of(2000, 1, 1));
        customer.setPhone("0123456789");
        customer.setAddress("Hà Nội");
        customer.setEmail("abc@example.com");

        boolean success = customerDAO.addCustomer(customer);

        System.out.println(success ? "Thêm thành công!" : "Thêm thất bại.");
    }
}

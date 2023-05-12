package com.example.yallahride.ServiceTest;


import com.example.yallahride.Entity.Car;
import com.example.yallahride.Entity.CarImage;
import com.example.yallahride.Entity.User;
import com.example.yallahride.Service.Interface.CarImageService;
import com.example.yallahride.Service.Interface.CarService;
import com.example.yallahride.Service.Interface.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarServiceTest {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @Autowired
    CarImageService carImageService;

    Car car;
    User user;


    @BeforeAll
    public void setup() {
        user = userService.saveUser(new User("Hassan", "Al-Shannag",
                "shnaqhassan@hotmail.com"));
        car = carService.saveCar(new Car("Black", "Ford", "Fusion",
                "19-$#$#$#", 2014, user));
    }

    @Test
    @Order(1)
    public void testAddCarImage() {
        CarImage carImage = new CarImage();
        carImage.setCar(car);
        carImage.setMultipartFile(new MockMultipartFile("carImage1.png","carImage1.png".getBytes()));


        CarImage carImage2 = new CarImage();
        carImage.setCar(car);
        carImage2.setMultipartFile(new MockMultipartFile("carImage2.png", "carImage2.png".getBytes()));

        carService.addCarImage(car.getId(), carImage);
        carService.addCarImage(car.getId(), carImage2);
        Assertions.assertTrue(carService.getAllCarImages(car.getId()).size() > 0);
    }

    @Test
    @Order(2)
    public void testDeleteCarImage() {
        Car car1 = carService.findCarById(car.getId());
        Long carImageId = car1.getCarImages().iterator().next().getId();
        carService.deleteCarImage(carImageId);
//        Assertions.assertThat(carImageService.findAllCarImages(car1.getId()). == null);
    }

    @AfterAll
    public void cleanup() {
        carService.deleteCarById(car.getId());
        userService.deleteUserById(user.getId());
    }
}
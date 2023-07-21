package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
//postman acip once create yapip olusturuyoruz
//ardindan post yapip create token ile admin ve password gonderip token aliyoruz
//sonra put diyip header a token girip post ile alinan id yazilarak degisiklik sagliyoruz.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        //This class will run the classes below in this order
        C01_CreateBooking.class,
        C02_UpdateBooking.class,
        C03_ReadBooking.class,
        C04_PatchBooking.class,
        C05_DeleteBooking.class,
        C06_ReadBooking_Negative.class

        })

public class Runner {
}
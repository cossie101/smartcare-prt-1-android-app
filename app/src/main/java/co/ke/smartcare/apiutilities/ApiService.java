package co.ke.smartcare.apiutilities;

import java.util.List;

import co.ke.smartcare.dataclasses.Appointment;
import co.ke.smartcare.dataclasses.Doctor;
import co.ke.smartcare.dataclasses.Enquiry;
import co.ke.smartcare.dataclasses.Login;
import co.ke.smartcare.dataclasses.Payment;
import co.ke.smartcare.dataclasses.Status;
import co.ke.smartcare.dataclasses.UploadInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("api/login")
    Call<Login> doLogin( @Query("username") String username, @Query("password") String password);

    @GET("api/signup")
    Call<UploadInfo> doSignUp( @Query("name") String name,
                               @Query("username") String username,
                               @Query("email") String email,
                               @Query("phone") String phone,
                               @Query("idno") String idno,
                               @Query("password") String password );

    @GET("api/pay/subscription")
    Call<UploadInfo> doSubscribe ( @Query("member") int member,
                                   @Query("amount") double amount);

    @GET("api/save/enquiry")
    Call<UploadInfo> saveEnquiry ( @Query("member") int member,
                                   @Query("title") String title,
                                   @Query("enquiry") String enquiry);

    @GET("api/load/enquiry")
    Call<List<Enquiry>> viewEnquiry ( @Query("member") int member);

    @GET("api/fetch/status")
    Call<List<Status>> loadStatus ( @Query("enquiry") int enquiry );

    @GET("api/save/appointment")
    Call<UploadInfo> saveAppointment( @Query("patient") int patient,
                                      @Query("title") String title,
                                      @Query("appointment") String appointment,
                                      @Query("appointmentDate") String appointmentDate);

    @GET("api/fetch/doctors")
    Call<List<Doctor>> fetchDocs();

    @GET("api/payment")
    Call<UploadInfo> makePayment( @Query("patient") int patient,
                                  @Query("amount") double amount);

    @GET("api/payments")
    Call<List<Payment>> fetchPayments( @Query("patient") int patient );

    @GET("api/fetch/appointments")
    Call<List<Appointment>> fetchAppointments( @Query("patient") int patient);



}

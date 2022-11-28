package com.fazpass.finance.ui.confirm_login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.fazpass.finance.MainActivity;
import com.fazpass.finance.R;
import com.fazpass.finance.helper.Storage;
import com.fazpass.finance.object.User;

import java.util.ArrayList;
import java.util.Iterator;

public class ConfirmLoginViewModel extends ViewModel {

    private User user;
    private boolean isCDAvailable;
    private ConfirmLoginFragment fragment;
    
    public void initialize(ConfirmLoginFragment fragment) {
        this.fragment = fragment;
    }

    public boolean isCDAvailable() {
        return isCDAvailable;
    }

    public void setCDAvailable(boolean CDAvailable) {
        isCDAvailable = CDAvailable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(@Nullable ArrayList<String> argsUser) {
        if (argsUser != null) {
            Iterator<String> iterator = argsUser.iterator();
            this.user = new User(iterator.next(), iterator.next(), iterator.next(), iterator.next(), iterator.next(), iterator.next());
        }
    }

    public void navigateUp() {
        Bundle args = new Bundle();
        args.putString("ARGS_EMAIL", user.getEmail());
        args.putString("ARGS_PHONE", user.getPhone());
        args.putString("ARGS_PIN", user.getPin());

        Navigation.findNavController(fragment.requireView())
                .navigate(R.id.action_confirmLoginFragment_to_loginFragment, args);
    }

    public void successLogin() {
        Storage.saveUser(fragment.requireContext(), user);

        Intent intent = new Intent(fragment.requireActivity(), MainActivity.class);
        fragment.startActivity(intent);
        fragment.requireActivity().finish();
    }

    public void failedLogin(String errorMessage) {
        fragment.showErrorMessage(errorMessage);
    }

    /*public void crossDeviceNotificationOption() {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.requireContext());
        builder.setTitle("Cross Device Notification")
                .setMessage(R.string.cd_notification_message)
                .setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        Fazpass.check(fragment.requireContext(), user.getEmail(), user.getPhone(), user.getPin(), new TrustedDeviceListener<FazpassTd>() {
            @Override
            public void onSuccess(FazpassTd o) {
                o.validateCrossDevice(fragment.requireContext(), 40, new CrossDeviceListener() {
                    @Override
                    public void onResponse(String device, boolean status) {
                        dialog.dismiss();

                        if (status) {
                            enroll(o);
                        } else {
                            fragment.showErrorMessage("Cross device validation has been declined.");
                        }
                    }

                    @Override
                    public void onExpired() {
                        dialog.dismiss();
                        fragment.showErrorMessage("Cross device validation is expired. Ready your device and try again.");
                    }
                });
            }

            @Override
            public void onFailure(Throwable err) {
                dialog.dismiss();
                failedLogin(fragment.getString(R.string.fazpass_init_failed_message, "cross device notification"));
            }
        });
    }

    public void smsVerificationOption() {
        final String[] otpId = new String[1];

        AlertDialog dialog = new DialogInputNumber(
                fragment, "SMS OTP Verification", R.string.sms_verification_message, 4,
                (alertDialog, input) -> {
                    if (otpId[0] != null) {
                        alertDialog.dismiss();
                        smsConfirmOtpNumber(input, otpId[0]);
                    } else {
                        Toast.makeText(fragment.getContext(), "Would you kindly wait for your message to arrive?", Toast.LENGTH_LONG).show();
                    }
                    return null;
                },
                alertDialog -> {
                    alertDialog.dismiss();
                    return null;
                }).getInstance();
        dialog.show();

        Fazpass.requestOtpByPhone(fragment.requireContext(), user.getPhone(), fragment.getString(R.string.otp_sms_gateway_key), new Otp.Request() {
            @Override
            public void onComplete(OtpResponse otpResponse) {
                otpId[0] = otpResponse.getOtpId();
            }

            @Override
            public void onIncomingMessage(String s) {
                dialog.dismiss();
                smsConfirmOtpNumber(s, otpId[0]);
            }

            @Override
            public void onError(Throwable throwable) {
                dialog.dismiss();
                failedLogin("Failed to request SMS Otp. Please try again later.");
            }
        });
    }

    private void smsConfirmOtpNumber(String inputtedOtp, String otpId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.requireContext());
        builder.setTitle("Confirming OTP")
                .setMessage(R.string.sms_confirming_message)
                .setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        Fazpass.validateOtp(fragment.requireContext(), otpId, inputtedOtp, new Otp.Validate() {
            @Override
            public void onComplete(boolean isSuccess) {
                dialog.dismiss();

                if (isSuccess) {
                    Fazpass.check(fragment.requireContext(), user.getEmail(), user.getPhone(), user.getPin(), new TrustedDeviceListener<FazpassTd>() {
                        @Override
                        public void onSuccess(FazpassTd fazpassTd) {
                            enroll(fazpassTd);
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            failedLogin("Failed to check device. Please try again.");
                        }
                    });
                } else {
                    failedLogin("Wrong OTP code.");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                dialog.dismiss();
                failedLogin("Failed to confirm otp. Please try again later.");
            }
        });
    }

    private void enroll(FazpassTd o) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.requireContext());
        builder.setTitle("Finishing Login")
                .setMessage("Please wait while we tidying up our table...")
                .setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        o.enrollDeviceByPin(fragment.requireContext(), user, user.getPin(), new TrustedDeviceListener<EnrollStatus>() {
            @Override
            public void onSuccess(EnrollStatus o) {
                dialog.dismiss();

                if (o.getStatus()) {
                    successLogin();
                } else {
                    fragment.showErrorMessage(o.getMessage());
                }
            }

            @Override
            public void onFailure(Throwable err) {
                dialog.dismiss();
                fragment.showErrorMessage("Failed to enroll this device. Check your internet and try again.");
            }
        });
    }*/
}
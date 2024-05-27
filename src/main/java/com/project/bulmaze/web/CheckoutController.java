package com.project.bulmaze.web;

import com.project.bulmaze.model.dto.UserDTO;
import com.project.bulmaze.payment.ChargeRequest;
import com.project.bulmaze.payment.StripeService;
import com.project.bulmaze.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;
import java.security.Principal;

@Controller
public class CheckoutController {

    private final StripeService paymentsService;
    private final UserService userService;

    public CheckoutController(StripeService paymentsService, UserService userService) {
        this.paymentsService = paymentsService;
        this.userService = userService;
    }

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 30 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model, Principal principal)
            throws StripeException, AuthenticationException {
        UserDTO userDTO = this.userService.findByUsername(principal.getName());

        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());

        userService.userPaidSuccessfully(userDTO);

        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

}

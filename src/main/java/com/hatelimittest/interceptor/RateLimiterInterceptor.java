package com.hatelimittest.interceptor;

import com.hatelimittest.config.RateLimiter;
import com.hatelimittest.entity.Parceiro;
import com.hatelimittest.entity.ParceiroRateLimit;
import com.hatelimittest.enums.PricingPlan;
import com.hatelimittest.repository.ParceiroRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Builder
@AllArgsConstructor
public class RateLimiterInterceptor implements HandlerInterceptor {

    private ParceiroRepository repository;

    private final RateLimiter rateLimiter;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String urlPath = request.getRequestURI();
        String login = request.getHeader("login");
        String method = request.getMethod();

        Parceiro parceiro = repository.findByLogin(login);

        if(parceiro.getRateLimit()) {
            Optional<ParceiroRateLimit> configByPath = parceiro.getParceiroRateLimits().stream()
                    .filter(obj -> obj.getPath().equalsIgnoreCase(urlPath) && obj.getMetodo().equalsIgnoreCase(method))
                    .findFirst();

            PricingPlan pricingPlan = configByPath.isPresent() ? configByPath.get().getPlan() : PricingPlan.FREE;
            String rateLimitLogin = login.concat(method).concat(urlPath);

            if (!rateLimiter.tryAccess(rateLimitLogin, pricingPlan)) {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                return false;
            }
        }

        return true;
    }
}

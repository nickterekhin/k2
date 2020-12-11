package com.terekhin.development.Controllers;

import com.terekhin.development.domain.Account;
import com.terekhin.development.domain.CrossCourse;
import com.terekhin.development.domain.Customer;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CustomersController extends Controller {

    public CustomersController() {
        this.tmplDir = "/customers";
    }

    public String index(WebContext webContext) throws NotificationService {

            List<Customer> customers = _dbCtx.Customers().getAll();

            for (Customer cust : customers) {
                double sum = 0;
                for (Account account : _dbCtx.Accounts().getAllByCustomerId(cust.getId())) {
                    if (account.getCurrency().getName().equals("UAH")) {
                        sum += account.getBalance();
                    } else {
                        CrossCourse cc = account.getCurrency().getCrossCoursesFrom().stream().filter(e -> e.getToCurrency().getName().equals("UAH")).findFirst().orElse(null);
                        if (cc != null)
                            sum += account.getBalance() * cc.getAmount();
                    }
                }
                cust.setTotalBalance(sum);
            }

            webContext.setVariable("customers", customers);
            return this.view("/pages/customers/list", webContext);
    }

    @Override
    public String create(WebContext webContext) throws NotificationService {
        Customer customer = new Customer();

        if(webContext.getRequest().getMethod().equals("POST"))
        {
            try{
            customer.setFirstName( webContext.getRequest().getParameter("FirstName"));
            customer.setLastName(webContext.getRequest().getParameter("LastName"));
                _dbCtx.Customers().create(customer);
                return this.viewRedirect(webContext,"User Added Successfully");
            }catch(NotificationService notify)
            {
                notify.show(webContext,NotificationType.ERROR);
            }

        }
            webContext.setVariable("customer", customer);
            return this.view("/pages/customers/add", webContext);
    }

    @Override
    public String update(WebContext webContext) throws NotificationService {

        String id =  webContext.getRequest().getParameter("id");
        Customer customer = _dbCtx.Customers().getById(Long.valueOf(id));
        if(webContext.getRequest().getMethod().equals("POST"))
        {
            try{
            customer.setFirstName(webContext.getRequest().getParameter("FirstName"));
            customer.setLastName(webContext.getRequest().getParameter("LastName"));
            _dbCtx.Customers().update(customer);
            return this.viewRedirect(webContext,"Customer Updated Successfully");
            }catch(NotificationService notify)
            {
                notify.show(webContext,NotificationType.ERROR);
            }

        }
        webContext.setVariable("customer", customer);
        return this.view("/pages/customers/edit", webContext);

    }

    @Override
    public String destroy(WebContext webContext) throws NotificationService {
        _dbCtx.Customers().delete(Long.valueOf(webContext.getRequest().getParameter("id")));
        return this.viewRedirect(webContext);
    }

}

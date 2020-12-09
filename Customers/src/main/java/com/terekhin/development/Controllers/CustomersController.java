package com.terekhin.development.Controllers;

import com.terekhin.development.domain.Account;
import com.terekhin.development.domain.CrossCourse;
import com.terekhin.development.domain.Customer;
import com.terekhin.development.helpers.ActionParser;
import com.terekhin.development.helpers.NotificationService;
import com.terekhin.development.helpers.NotificationType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CustomersController extends Controller {

    public CustomersController() {
        this.tmpl_dir = "/customers";
    }

    @Override
    public void getModel(HttpServletRequest request, HttpServletResponse response, WebContext webContext, TemplateEngine tmpl) throws Exception {

        ActionParser actionParser = ActionParser.getResponseAction(request.getParameter("action"));

        switch(actionParser)
        {
            case DELETE:
                _dbCtx.Customers().delete(Long.valueOf(request.getParameter("id")));
                this.viewRedirect(response);
            break;
            case ADD:
                Customer customer = new Customer();

                if(request.getMethod().equals("POST"))
                {
                    customer.setFirstName(request.getParameter("FirstName"));
                    customer.setLastName(request.getParameter("LastName"));
                    try {
                        _dbCtx.Customers().create(customer);
                        this.viewRedirect(request,response,"User Added Successfully");
                    }catch(NotificationService notify)
                    {
                        notify.show(request, NotificationType.ERROR);
                    }
                }
                if(!response.isCommitted())
                {
                    request.setAttribute("customer", customer);
                    tmpl.process("/pages/customers/add", webContext, response.getWriter());
                }
                break;
            case EDIT:
                String id = request.getParameter("id");
                customer = _dbCtx.Customers().getById(Long.valueOf(id));
                if(request.getMethod().equals("POST"))
                {
                    customer.setFirstName(request.getParameter("FirstName"));
                    customer.setLastName(request.getParameter("LastName"));
                    try {

                        _dbCtx.Customers().update(customer);
                        this.viewRedirect(request,response,"Customer Updated Successfully");
                    }catch (NotificationService notify)
                    {
                        notify.show(request, NotificationType.ERROR);
                    }
                }
                if(!response.isCommitted()) {
                    request.setAttribute("customer", customer);
                    tmpl.process("/pages/customers/edit", webContext, response.getWriter());
                }

                break;

            default:
                List<Customer> customers =  _dbCtx.Customers().getAll();
                for(Customer cust : customers)
                {
                    double sum =0;
                    for(Account account : _dbCtx.Accounts().getAllByCustomerId(cust.getId()))
                    {
                        if(account.getCurrency().getName().equals("UAH"))
                        {
                            sum+=account.getBalance();
                        }else
                        {
                            CrossCourse cc = account.getCurrency().getCrossCoursesFrom().stream().filter(e->e.getToCurrency().getName().equals("UAH")).findFirst().orElse(null);
                            if(cc!=null)
                            sum+=account.getBalance()*cc.getAmount();
                        }
                    }
                    cust.setTotalBalance(sum);
                }

                webContext.setVariable("customers",customers);
                tmpl.process("/pages/customers/list", webContext, response.getWriter());
        }



    }
}

package vn.elca.training.error;

import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;


import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ErrorAppController implements ErrorController {
	
	@Autowired
	  private ErrorAttributes errorAttributes;

    
    @Autowired
    public ErrorAppController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    /* Return the error page path. */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    // Handle the /error path invoke.
    @RequestMapping("/error")
   /* @ResponseBody annotation will return the error page content instead of the template error page name. */
    @ResponseBody
    public String processError(HttpServletRequest request, WebRequest webRequest) {

        // Get error status code.
        Integer statusCode = (Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(statusCode.intValue() == 500)
        {
            // If you want to return template error page, then remove the @ResponseBody annotation of this method.
            return "error-500.html";
        }else if(statusCode.intValue() == 450)
        {
            // If you want to return template error page, then remove the @ResponseBody annotation of this method.
            return "error-450.html";
        }
        else
        {
        	
                // Get error message.
                String message = (String)request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

                // Get exception object.
                Exception exception = (Exception)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

                

                StringBuffer retBuf = new StringBuffer();
                retBuf.append("<pre>");

                if(statusCode != null)
                {
                    retBuf.append("Status Code : ");
                    retBuf.append(statusCode);
                }
                if(exception !=null) {
                	retBuf.append("Exception : ");
                    retBuf.append(exception);
                }

                if(message != null && message.trim().length() > 0)
                {
                    retBuf.append("\n\rError Message : ");
                    retBuf.append(message);
                }
                
                
                retBuf.append("</pre>");
            return String.format("<head>\r\n" + 
            		"\r\n" + 
            		"<meta charset=\"utf-8\">\r\n" + 
            		"\r\n" + 
            		"<!-- Latest compiled and minified CSS -->\r\n" + 
            		"<link rel=\"stylesheet\"\r\n" + 
            		"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css\">\r\n" + 
            		"\r\n" + 
            		"<!-- Optional theme CSS -->\r\n" + 
            		"<link rel=\"stylesheet\"\r\n" + 
            		"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.css\">\r\n" + 
            		"<link rel=\"stylesheet\"\r\n" + 
            		"	href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\r\n" + 
            		"\r\n" + 
            		"<!-- Private CSS -->\r\n" + 
            		"\r\n" + 
            		"<link href=\"/css/common.css\" rel=\"stylesheet\">\r\n" + 
            		"\r\n" + 
            		"<link href=\"/css/error.css\" rel=\"stylesheet\">\r\n" + 
            		"<!-- JQuery JS -->\r\n" + 
            		"<script type=\"text/javascript\"\r\n" + 
            		"	src=\"https://code.jquery.com/jquery-2.1.4.js\"></script>\r\n" + 
            		"<script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\r\n" + 
            		"<script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\r\n" + 
            		"\r\n" + 
            		"<script\r\n" + 
            		"	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js\"></script>\r\n" + 
            		"\r\n" + 
            		"<!-- Private JS -->\r\n" + 
            		"<title></title>\r\n" + 
            		"</head>\r\n" + 
            		"<body>\r\n" + 
            		"	<div class=\"container-fluid\">\r\n" + 
            		"		<div class=\"navbar-header\">\r\n" + 
            		"			<img class=\"main-logo--plain\" alt=\"ELCA Vietnam\"\r\n" + 
            		"				src=\"/image/elca.PNG\" width=\"142\" height=\"50\"> <strong>Project\r\n" + 
            		"				Information Management</strong>\r\n" + 
            		"		</div>\r\n" + 
            		"		<div class=\"collapse navbar-collapse\" id=\"myNavbar\">\r\n" + 
            		"			<ul class=\"list-inline\">\r\n" + 
            		"				<li><a href=\"#\" class=\"black-link\">EN</a> | <a href=\"#\">FR</a></li>\r\n" + 
            		"				<li></li>\r\n" + 
            		"				<li></li>\r\n" + 
            		"				<li><a href=\"#\"><strong>Help</strong></a></li>\r\n" + 
            		"				<li><a href=\"#\" class=\"disabled\">Log out</a></li>\r\n" + 
            		"			</ul>\r\n" + 
            		"		</div>\r\n" + 
            		"	</div>\r\n" + 
            		"	<div class=\"container-fluid text-center\">\r\n" + 
            		"		<div class=\"row content\">\r\n" + 
            		"\r\n" + 
            		"			<table>\r\n" + 
            		"				<tr>\r\n" + 
            		"					<td><img class=\"main-logo--plain floatleft\" alt=\"ELCA Vietnam\"\r\n" + 
            		"						src=\"/image/close_delete-256.png\" /></td>\r\n" + 
            		"					<td><p class=\"error-text\">\r\n" + 
            		"							<strong>Unexpected error occurred.<br> Please <span\r\n" + 
            		"								class=\"error-text-color\">contact your administrator.</span>\r\n" + 
            		"								<br><br>\r\n" + 
            		"									or <a href=\"/projectlist\">back to search project</a></strong>\r\n" + 
            		"						</p></td>\r\n" + 
            		"				</tr>\r\n" + 
            		"			</table>\r\n" + 
            		"		</div>\r\n" + 
            		"	</div>\r\n" + 
            		"</body>");
                
        	 
        }
        

    }
}
<%@ page import="domain.Book" %><%--
  Created by IntelliJ IDEA.
  User: stha
  Date: 4/9/18
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--jsp scriplet section to get data from controller, in any--%>

<%--add and update will be done via same page--%>
<%
    Book bookInfo = null;
    if (request.getAttribute("bookInfo") != null) {
        bookInfo = (Book) request.getAttribute("bookInfo");
    }
%>
<%--include header--%>
<jsp:include page="/header.jsp"/>

<%--page content--%>

<%--just have <div> </div> with class container section , other <html> <body> tags will be included via header.jsp and footer.jsp--%>

<div class="container">
    <div class="col-lg-offset-12">
        <span class="pull-right"><a href="/book" class="btn btn-primary">Book List</a> </span>
    </div>
    <div class="col-md-3">
        <a href="#">
            <img alt="Logo" src="${pageContext.request.contextPath}/includes/images/logo.png" class="img-responsive">
        </a>
    </div>
    <div class="col-md-6">
        <form class="form-horizontal" role="form"
              action="${pageContext.request.contextPath}/book?action=<%=bookInfo!=null?"updateBook":"addNew"%>"
              method="post">
            <fieldset>
                <legend><%=bookInfo != null ? "Update" : "Add"%> Book</legend>
                <%if (bookInfo != null) { %>
                <%--if only we have data in bookInfo set hidden input--%>
                <input type="hidden" name="id" value="<%=bookInfo.getId()%>">
                <input type="hidden" name="purchased_date" value="<%=bookInfo.getPurchased_date()%>">
                <% }%>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-book"></span></div>
                        <input type="text" name="name" class="form-control" placeholder="Name of Book"
                               value="<%=bookInfo!=null?bookInfo.getName():""%>" required autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                        <input type="text" name="author" class="form-control" placeholder="Book Author"
                               value="<%=bookInfo!=null?bookInfo.getAuthor():""%>" required autofocus>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-new-window"></span></div>
                        <input type="text" name="isbn" class="form-control" placeholder="ISBN"
                               value="<%=bookInfo!=null?bookInfo.getIsbn():""%>" required>

                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-check"></span></div>
                        <input type="text" name="category" class="form-control" placeholder="Category"
                               value="<%=bookInfo!=null?bookInfo.getCategory():""%>" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
                        <input type="number" step="0.01" name="price" class="form-control" placeholder="Price"
                               value="<%=bookInfo!=null?bookInfo.getPrice():""%>" required>

                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-6">
                        <button class="btn btn-primary form-control"
                                type="submit"><%=bookInfo != null ? "Update" : "Add"%>
                        </button>
                    </div>
                    <div class="col-lg-6">
                        <%=bookInfo != null ? "<a href=\"/book\" class=\"btn btn-default form-control\">Cancel</a>" : "<button class=\"btn btn-default form-control\" type=\"reset\">Reset</button>"%>
                        <%--<button class="btn btn-default form-control" type="reset">Reset</button>--%>
                        <%--<a href="users" class="btn btn-default form-control">Cancel</a>--%>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>

</div>
<!-- /.container -->
<%--page content end--%>
<%--include footer--%>
<jsp:include page="/footer.jsp"/>

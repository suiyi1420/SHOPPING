
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/layouts/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>商品管理</title>
    <link rel="stylesheet" href="${ctxsta}/common/bootstrap-table/bootstrap-table.min.css" />
</head>
<body>

    <table id="table"
           data-toggle="table"
           data-height="400"
           data-search="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-show-export="true"
           data-show-pagination-switch="true"
           data-show-columns="true"
    <%--data-detail-formatter="detailFormatter"--%>
           data-url="${ctx}/product/list/gid/0"
           data-page-size="20"
           data-page-list="[20, 50, 100, 200]"
           data-side-pagination="server"
           data-striped="true"
           data-pagination="true"
           data-sort-order="desc"
           data-toolbar="#toolbar">
        <thead>
        <tr>
            <th data-field="productId" data-halign="center" data-align="center" data-sortable="true">商品ID</th>
            <th data-field="productNumber" data-halign="center" data-align="center" data-sortable="true">商品编号</th>
            <th data-field="labelId" data-halign="center" data-align="center" data-sortable="true">标签ID</th>
            <th data-field="name" data-halign="center" data-align="center" data-sortable="true">商品名称</th>
            <th data-field="showPrice" data-halign="center" data-align="center" data-sortable="true">商品价格</th>
            <th data-field="showInShelve" data-formatter="inShellFormatter" data-halign="center" data-align="center" data-sortable="true">是否上架</th>
            <th data-field="showInNav" data-formatter="navFormatter" data-halign="center" data-align="center" data-sortable="true">是否导航</th>
            <th data-field="showInTop" data-formatter="topFormatter" data-halign="center" data-align="center" data-sortable="true">是否置顶</th>
            <th data-field="showInHot" data-formatter="hotFormatter" data-halign="center" data-align="center" data-sortable="true">是否热门</th>
            <th data-formatter="actionFormatter" data-events="actionEvents" data-halign="center" data-align="center" data-sortable="true">操作</th>
        </tr>
        </thead>
    </table>


<myfooter>
    <!-- Bootstrap table -->
    <script src="${ctxsta}/common/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctxsta}/common/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
    <script src="${ctxsta}/common/bootstrap-table/tableExport.js"></script>
    <script src="${ctxsta}/common/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctxsta}/cms/js/product.js"></script>
</myfooter>
</body>
</html>

var if_firstime = true;  
	var pageIndex = Number("[[${page.pageno}]]");
	var totalPage = Number("[[${page.totalpage}]]");
	var pageSize = Number("[[${page.pagesize}]]");
	var count=Number("[[${page.totalcount}]]");
	/* console.log(pageIndex);
	console.log(totalPage);
	console.log(pageSize); */
    $.jqPaginator('#pagination1', {
      	totalPages: totalPage,
       /*  totalCounts: count,
        pageSize:10, */
        visiblePages: 5,
        currentPage: pageIndex,
        onPageChange: function (num) {
        	if(if_firstime){  
                if_firstime = false;  
            }else if(!if_firstime){  
            	var url="seven/person/personPage";
            		var data={};
            		data.pageno=num;
            		console.log(data.pageno);
	          	   $.post(url,data,function(page){
	          		   $("#personList").html(page);
	          	   });  
            } 
           
        }
    });
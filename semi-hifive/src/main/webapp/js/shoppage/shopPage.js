$(".radio-input input").on("change", function () {
  // const input = $(".radio-input input").val();
  const input = $("input[name='value-radio']:checked").val();
  if (input == "value-1") {
    $("#shopProductContainer").css("display", "block");
    $("#reviewContainer").css("display", "none");
  } else if (input == "value-2") {
    $("#shopProductContainer").css("display", "none");
    $("#reviewContainer").css("display", "block");
  }
});

function numberWithCommas(x) {     
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); 
} 

$(document).on("click","#rsoon", e=>{
	console.log($("input[name=userId]").val())
	$.ajax({
		url: getContextPath() + "/rsoon",
		type: "POST",
		dataType: "json",
		data: { "userId": $("input[name=userId]").val() },
		success:function(rsoon){
			var html = "";
			console.log(rsoon);
			
			$.each(rsoon,function(index,item){
			var price=numberWithCommas(parseInt(item.product.price));
			html +=
				"<div id='pimgWraper'>"
              +"<div class='con-like'>"
                +"<input title='like' type='checkbox' class='like' />"
                +"<div class='checkmark'>"
                  +"<svg viewBox='0 0 24 24' class='outline' xmlns='http://www.w3.org/2000/svg'>"
                  +"<path d='M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z'></path></svg>"
                 + "<svg viewBox='0 0 24 24'class='filled'xmlns='http://www.w3.org/2000/svg'><path d='M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z'></path></svg>"
                  +"<svg class='celebrate' width='100' height='100' xmlns='http://www.w3.org/2000/svg' >"
                    +"<polygon points='10,10 20,20' class='poly'></polygon>"
                    +"<polygon points='10,50 20,50' class='poly'></polygon>"
                    +"<polygon points='20,80 30,70' class='poly'></polygon>"
                    +"<polygon points='90,10 80,20' class='poly'></polygon>"
                    +"<polygon points='90,50 80,50' class='poly'></polygon>"
                    +"<polygon points='80,80 70,70' class='poly'></polygon>"
                  +"</svg>"
                +"</div>"
              +"</div>"
              +"<a href="+getContextPath()+"/productpage?no="+item.product.productId+">"
               +"<img src='"+getContextPath()+"/upload/productRegist/"+item.productFile.imageName+"'alt=''/>"
               + "<p id='productName'>"+item.product.title+"</p>"
               + "<p id='productPrice'>"+price+"원"+ "<span>"+item.product.registTime+"</span></p>"
                +"<p id='productPlace'>"
                 + "<ion-icon name='location-outline'></ion-icon>"
                  +item.product.areaName
                +"</p>"
              +"</a>"
            +"</div>"
            })
            $("#productImgContainer").empty();
            $("#productImgContainer").append(html);
		}		
	})
})

$(document).on("click","#psoon", e=>{
	console.log($("input[name=userId]").val())
	$.ajax({
		url: getContextPath() + "/psoon",
		type: "POST",
		dataType: "json",
		data: { "userId": $("input[name=userId]").val() },
		success:function(psoon){
			var html = "";
			console.log(psoon);
			
			$.each(psoon,function(index,item){
				var price=numberWithCommas(parseInt(item.product.price));
			html +=
				"<div id='pimgWraper'>"
              +"<div class='con-like'>"
                +"<input title='like' type='checkbox' class='like' />"
                +"<div class='checkmark'>"
                  +"<svg viewBox='0 0 24 24' class='outline' xmlns='http://www.w3.org/2000/svg'>"
                  +"<path d='M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z'></path></svg>"
                 + "<svg viewBox='0 0 24 24'class='filled'xmlns='http://www.w3.org/2000/svg'><path d='M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z'></path></svg>"
                  +"<svg class='celebrate' width='100' height='100' xmlns='http://www.w3.org/2000/svg' >"
                    +"<polygon points='10,10 20,20' class='poly'></polygon>"
                    +"<polygon points='10,50 20,50' class='poly'></polygon>"
                    +"<polygon points='20,80 30,70' class='poly'></polygon>"
                    +"<polygon points='90,10 80,20' class='poly'></polygon>"
                    +"<polygon points='90,50 80,50' class='poly'></polygon>"
                    +"<polygon points='80,80 70,70' class='poly'></polygon>"
                  +"</svg>"
                +"</div>"
              +"</div>"
               +"<a href="+getContextPath()+"/productpage?no="+item.product.productId+">"
            +"<img src='"+getContextPath()+"/upload/productRegist/"+item.productFile.imageName+"'alt=''/>"
               + "<p id='productName'>"+item.product.title+"</p>"
               + "<p id='productPrice'>"+price+"원"+ "<span>"+item.product.registTime+"</span></p>"
                +"<p id='productPlace'>"
                 + "<ion-icon name='location-outline'></ion-icon>"
                  +item.product.areaName
                +"</p>"
              +"</a>"
            +"</div>"
            })
            $("#productImgContainer").empty();
            $("#productImgContainer").append(html);
		}		
	})
})

$(document).on("click","#hsoon", e=>{
	console.log($("input[name=userId]").val())
	$.ajax({
		url: getContextPath() + "/hsoon",
		type: "POST",
		dataType: "json",
		data: { "userId": $("input[name=userId]").val() },
		success:function(hsoon){
			var html = "";
			console.log(hsoon.product);
			console.log(hsoon.productfile);
			$.each(hsoon,function(index,item){
			var price=numberWithCommas(parseInt(item.product.price));
			html +=
				"<div id='pimgWraper'>"
              +"<div class='con-like'>"
                +"<input title='like' type='checkbox' class='like' />"
                +"<div class='checkmark'>"
                  +"<svg viewBox='0 0 24 24' class='outline' xmlns='http://www.w3.org/2000/svg'>"
                  +"<path d='M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z'></path></svg>"
                 + "<svg viewBox='0 0 24 24'class='filled'xmlns='http://www.w3.org/2000/svg'><path d='M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z'></path></svg>"
                  +"<svg class='celebrate' width='100' height='100' xmlns='http://www.w3.org/2000/svg' >"
                    +"<polygon points='10,10 20,20' class='poly'></polygon>"
                    +"<polygon points='10,50 20,50' class='poly'></polygon>"
                    +"<polygon points='20,80 30,70' class='poly'></polygon>"
                    +"<polygon points='90,10 80,20' class='poly'></polygon>"
                    +"<polygon points='90,50 80,50' class='poly'></polygon>"
                    +"<polygon points='80,80 70,70' class='poly'></polygon>"
                  +"</svg>"
                +"</div>"
              +"</div>"
              +"<a href="+getContextPath()+"/productpage?no="+item.product.productId+">"
              +"<img src='"+getContextPath()+"/upload/productRegist/"+item.productFile.imageName+"'alt=''/>"
               + "<p id='productName'>"+item.product.title+"</p>"
               + "<p id='productPrice'>"+price+"원"+ "<span>"+item.product.registTime+"</span></p>"
                +"<p id='productPlace'>"
                 + "<ion-icon name='location-outline'></ion-icon>"
                  +item.product.areaName
                +"</p>"
              +"</a>"
            +"</div>"
            })
            $("#productImgContainer").empty();
            $("#productImgContainer").append(html);
		}		
	})
})

$(document).on("click","#lsoon", e=>{
	console.log($("input[name=userId]").val())
	$.ajax({
		url: getContextPath() + "/lsoon",
		type: "POST",
		dataType: "json",
		data: { "userId": $("input[name=userId]").val() },
		success:function(lsoon){
			var html = "";
			console.log(lsoon);
			
			$.each(lsoon,function(index,item){
			var price=numberWithCommas(parseInt(item.product.price));
			html +=
				"<div id='pimgWraper'>"
              +"<div class='con-like'>"
                +"<input title='like' type='checkbox' class='like' />"
                +"<div class='checkmark'>"
                  +"<svg viewBox='0 0 24 24' class='outline' xmlns='http://www.w3.org/2000/svg'>"
                  +"<path d='M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z'></path></svg>"
                 + "<svg viewBox='0 0 24 24'class='filled'xmlns='http://www.w3.org/2000/svg'><path d='M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z'></path></svg>"
                  +"<svg class='celebrate' width='100' height='100' xmlns='http://www.w3.org/2000/svg' >"
                    +"<polygon points='10,10 20,20' class='poly'></polygon>"
                    +"<polygon points='10,50 20,50' class='poly'></polygon>"
                    +"<polygon points='20,80 30,70' class='poly'></polygon>"
                    +"<polygon points='90,10 80,20' class='poly'></polygon>"
                    +"<polygon points='90,50 80,50' class='poly'></polygon>"
                    +"<polygon points='80,80 70,70' class='poly'></polygon>"
                  +"</svg>"
                +"</div>"
              +"</div>"
               +"<a href="+getContextPath()+"/productpage?no="+item.product.productId+">"
              +"<img src='"+getContextPath()+"/upload/productRegist/"+item.productFile.imageName+"'alt=''/>"
               + "<p id='productName'>"+item.product.title+"</p>"
               + "<p id='productPrice'>"+price+"원"+ "<span>"+item.product.registTime+"</span></p>"
                +"<p id='productPlace'>"
                 + "<ion-icon name='location-outline'></ion-icon>"
                  +item.product.areaName
                +"</p>"
              +"</a>"
            +"</div>"
            })
            $("#productImgContainer").empty();
            $("#productImgContainer").append(html);
		}		
	})
})

$(document).ready(function() {
	$("#categoryFunction span").click(function() {
	  $(this).addClass("active");
	$("#categoryFunction span").not(this).removeClass("active");
	});
});
var close = document.getElementsByClassName('close')[0];
var open = document.getElementsByClassName('top-l')[0];
var loginWrap = document.getElementsByClassName('top-r')[0];
var clear1 =  document.getElementsByClassName('close')[1];
var input1 = document.getElementsByTagName('input')[0];
var clear2 =  document.getElementsByClassName('close')[2];
var input2 = document.getElementsByTagName('input')[1];
var login = document.getElementsByTagName('input')[2];
var checked = document.getElementsByTagName('input')[3];
var newCount = document.getElementsByClassName('newCount')[0];
var iframe = document.getElementById('iframe1');
addEventListener(checked,'click',function(){
    if(checked.hasAttribute('checked')){
        checked.removeAttribute('checked');
    }else{
        checked.setAttribute('checked','checked');
    }
})
addEventListener(close,'click',function(){
    loginWrap.style.display = 'none';
    if(checked.hasAttribute('checked')){
        input2.value = '';
    }else{
        input1.value = '';
        input2.value = '';
    }
});
addEventListener(open,'click',function(){
    loginWrap.style.display = 'block';
})
addEventListener (input1,'focus',function(){
    input1.style.color = 'black';
})

addEventListener (input2,'focus',function(){
    input2.style.color = 'black';
})
addEventListener (input2, 'keypress',function(e){
    if(e.keyCode == 13){
        Login();
        $('.login').focus();   
    }
})
addEventListener (login,'click',function(){
    Login();
})
addEventListener (clear1,'click',function(){
    input1.value = '';
});
addEventListener (clear2,'click',function(){
    input2.value = '';
});
function Login(){
    $.getJSON('/MutualAid/AdminLogin',{"adminNum":input1.value.toString(),'pwd':input2.value.toString()},function(data){
        if(data['state'] == "true"){ //{"state":"true"}
            window.location.href = "../manager.html"; 
        }else{
            alert('出错了');
        }
    })
}


function getScroll(){      /*获取滚动条长度的方法*/
    if(window.pageXOffset){
        return{
            X: window.pageXOffset,
            y: window.pageYOffset
        }
    }
    else{
        return{
            x: document.body.scrollLeft + document.documentElement.scrollLeft,
            y: document.body.scrollTop + document.documentElement.scrollTop
        }
    }
}

function getViewSize(){             /* 这是获取窗口尺寸的方法 */
    if(window.innerHeight){
        return{
            h: window.innerHeight,
            w: window.innerWidth
        }  
    }else{
        if(window.compatMode == "CSS1Compat"){
            return{
                h: documnet.documentElement.clientheight,
                w: document.documentElement.clientWidth
            }
        }else{
            return{
                h: document.body.clientHeight,
                w: document.body.clientWidth
            }
        }
    }  
}

Element.prototype.getElementSize = function(){/*这是获取窗口尺寸的方法*/
    var elem = this.getBoundingClientRect();
    if(elem.width){
        return{
            h: elem.height,
            w: elem.width
        }
    }else{
        return{
            h: elem.bottom - elem.top,
            w: elem.right - elem.left
        }
    }
}

    /*这是获取元素属性的方法，prop和fake要以字符形式传入*/
function getStyle (elem,prop,fake){     
    var fake = fake || null ;
    if(elem.currentStlye){
        return elem.currentStlye[prop];
    }else{
        return window.getComputedStyle(elem,fake)[prop];
    }
}
/*******************************7/23**************************************/
function drag (elem){                        /*拖拽函数*/
    /*因为全局都要用到，所以得设置成全局变量*/
    var disX,                                    
        disY;
    elem.addEventListener('mousedown',function(e){
        disX = e.clientX - parseInt(getStyle(this,'left'));
        disY = e.clientY - parseInt(getStyle(this,'top'));
        document.addEventListener('mousemove',move,false);     
        /*因为下一次点击的时候要重新开始，
           所以要添加在按下监听里再次绑定*/
    },false);
    function move(e){
        elem.style.left = e.clientX - disX + 'px';
        elem.style.top = e.clientY - disY + 'px';
    }
    elem.addEventListener('mouseup',function(e){
        document.removeEventListener('mousemove',move,false);
    },false);
}

/*************这是兼容的绑定函数***********  因为attachEvent的this指向 window*/
function addEventListener(elem,type,handle){
    if(elem.addEventListener){
        elem.addEventListener(type,handle,false);
    }else if(elem.attachEvent){
        var typeHandle = handle;        /*typeHandle是用来接收handle来改变this指向的*/
        elem.attachEvent('on'+type,tempHandle); /*temp只是用来代替匿名函数的*/
        function tempHandle(){
            typeHandle.call(elem);
        }
    }else{
        elem['on' + type] = handle;
    }
}
/***************解绑函数************************* */
function removeEventListener(elem,type,handle){
    var typeHandle = handle;
    if(elem.removeEventListener){
        elem.removeEventListener(type,typeHandle,false);
    }else if(elem.detachEvent){
        elem.detachEvent('on' + type,typeHandle);
    }else{
        elem['on' + type] = null;
    }
}
/***************取消冒泡************************ */
/*************需要传入参数e******************* */
function stopBubble(e){
    e = e || window.event;
    if (e.stopPropagation){
        e.stopPropagation();
    }else{
        e.cancelBubble = true;
    }
}
/*******************取消默认事件****************** */
/* 弹出右键是默认事件contexmenu，注意是事件，dom是一般是document*/
/* 也可以用来取消a标签的默认事件*/
function cancelHandler(event) {
    event = event || window.event;
    if(event.preventDefault) {
        event.preventDefault();
    }else{
        event.returnValue = false;
    }
}
/**************** 获取事件源******************** */
/* 火狐event.target  || ie event.srcElement */
/*************这个方法直接返回事件源********* */
/*利用事件源对象和事件冒泡来处理的方式就叫做事件委托*/
function target (e){
    e = e || window.event;
    var tar = e.target || e.srcElement;
    return tar;
}
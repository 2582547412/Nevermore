<template>
<div class="main">
<h1>{{ title }}</h1>
</div>
</template>
<script>
export default {
name: "Main",
data:function () {
return{
title:'主页'
}
}
}
</script>
<!-- 局部的要写scoped-->
<style scoped>
.main {
/*vh:相对于视窗的高度，那么vw:则是相对于视窗的宽度*/
height: 100vh; /*100vh代表网页撑满一个屏*/
background-color: orange;
}
h1 {
margin: 0; /*去除h1标签自带的margin边距*/
color: red;
}
</style>
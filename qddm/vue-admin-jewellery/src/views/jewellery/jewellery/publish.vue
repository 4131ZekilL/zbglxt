<template>

    <div class="app-container">
  
      <h2 style="text-align: center;">发布珠宝</h2>
  
      <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px;">
        <el-step title="填写珠宝基本信息"/>
        <el-step title="编辑大纲"/>
        <el-step title="最终发布"/>
      </el-steps>
  
      <div class="ccInfo">
      <img :src="publishJewellery.cover">
      <div class="main">
        <h2>{{ publishJewellery.title }}</h2>
        <p><span>所属分类：{{ publishJewellery.classofyLevelOne }} — {{ publishJewellery.classofyLevelTwo }}</span></p>
        <p>设计师：{{ publishJewellery.designerName }}</p>
        <h3 class="red">￥{{ publishJewellery.price }}</h3>
      </div>
    </div>

    <div>
      <el-button @click="previous">返回修改</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="publish">发布珠宝</el-button>
    </div>
  </div>
  </template>
  
  <script>
import jewellery from '@/api/jewellery/jewellery'
  export default {
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        jewelleryId:'',
        publishJewellery:{}
      }
    },
  
    created() {
      if(this.$route.params&& this.$route.params.id){
        this.jewelleryId = this.$route.params.id

        this.getJewelleryPublishId()

      }
    },
  
    methods: {
      getJewelleryPublishId(){
        jewellery.getPublishInfo(this.jewelleryId)
          .then(response=>{
            this.publishJewellery =  response.data.publishJewellery
          })
      },
      previous() {
        console.log('previous')
        this.$router.push({ path: '/jewellery/chapter/'+this.jewelleryId})
      },
  
      publish() {
        jewellery.updateJewelleryStatus(this.jewelleryId)
            .then(response=>{
              this.$message({
                    type: 'success',
                    message: '珠宝发布成功!'
                });

            this.$router.push({ path: '/jewellery/list' })
            })
       
      }
    }
  }
  </script>
  <style scoped>
  .ccInfo {
      background: #f5f5f5;
      padding: 20px;
      overflow: hidden;
      border: 1px dashed #DDD;
      margin-bottom: 40px;
      position: relative;
  }
  .ccInfo img {
      background: #d6d6d6;
      width: 500px;
      height: 278px;
      display: block;
      float: left;
      border: none;
  }
  .ccInfo .main {
      margin-left: 520px;
  }
  
  .ccInfo .main h2 {
      font-size: 28px;
      margin-bottom: 30px;
      line-height: 1;
      font-weight: normal;
  }
  .ccInfo .main p {
      margin-bottom: 10px;
      word-wrap: break-word;
      line-height: 24px;
      max-height: 48px;
      overflow: hidden;
      
  }
  
  .ccInfo .main p {
      margin-bottom: 10px;
      word-wrap: break-word;
      line-height: 24px;
      max-height: 48px;
      overflow: hidden;
  }
  .ccInfo .main h3 {
      left: 540px;
      bottom: 20px;
      line-height: 1;
      font-size: 28px;
      color: #d32f24;
      font-weight: normal;
      position: absolute;
  }
  </style>
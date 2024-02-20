<template>

    <div class="app-container">
  
      <h2 style="text-align: center;">发布珠宝</h2>
  
      <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
        <el-step title="填写珠宝基本信息"/>
        <el-step title="编辑大纲"/>
        <el-step title="最终发布"/>
      </el-steps>
  
      <el-form label-width="120px">
  
        <el-form-item label="珠宝标题">
    <el-input v-model="jewelleryInfo.title" placeholder=" 示例：天然玉石：矿石。"/>
  </el-form-item>

  <!-- 所属分类 TODO -->
  <el-form-item label="珠宝分类">
    <el-select
        v-model="jewelleryInfo.classofyParentId"
        placeholder="请选择一级分类" @change="classofyLevelOneChanged">
        <el-option
        v-for="classofy in classofyOneList"
        :key="classofy.id"
        :label="classofy.title"
        :value="classofy.id"/>
  </el-select>
  <el-select
        v-model="jewelleryInfo.classofyId"
        placeholder="请选择二级分类">
        <el-option
        v-for="classofy in classofyTwoList"
        :key="classofy.id"
        :label="classofy.title"
        :value="classofy.id"/>
  </el-select>
</el-form-item>

  <!-- 设计师 TODO -->
  <el-form-item label="设计师">
    <el-select
        v-model="jewelleryInfo.designerId"
        placeholder="请选择设计师">
        <el-option
        v-for="designer in designerList"
        :key="designer.id"
        :label="designer.name"
        :value="designer.id"/>
  </el-select>
</el-form-item>
  <!-- 珠宝简介 TODO -->
  <el-form-item label="珠宝简介">
    <tinymce :height="300" v-model="jewelleryInfo.description"/>
</el-form-item>

  <!-- 珠宝封面 TODO -->
  <el-form-item label="珠宝封面">

<el-upload
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
    :action="BASE_API+'/bysjoss/fileoss'"
    class="avatar-uploader">
    <img :src="jewelleryInfo.cover">
</el-upload>

</el-form-item>

  <el-form-item label="珠宝价格">
    <el-input-number :min="0" v-model="jewelleryInfo.price" controls-position="right" placeholder="请填写珠宝的价格"/> 元
  </el-form-item>

  <el-form-item>
    <el-button :disabled="saveBtnDisabled" type="primary" @click="saveJewellery">保存并下一步</el-button>
  </el-form-item>
      </el-form>
    </div>
  </template>

  <script>
    import jewellery from '@/api/jewellery/jewellery'
    import classofy from '@/api/jewellery/classofy'
    import Tinymce from '@/components/Tinymce'
export default{
    components: { Tinymce },
    data(){
        return{
            saveBtnDisabled:false,
            jewelleryInfo:{
                title: '',
                classofyId: '',
                designerId: '',
                classofyParentId:'',
                description: '',
                cover: '/static/hmbb1.jpg',
                price: 0
            },
            designerList:[],
            classofyOneList:[],
            classofyTwoList:[],
            BASE_API: process.env.BASE_API,
            jewelleryId:''
        }
    },
    watch:{
      $route(to,from){
        this.jewelleryInfo={}
      }
    },
    created(){
        
        if(this.$route.params && this.$route.params.id){
        this.jewelleryId = this.$route.params.id
        this.getInfo()
      }else{
        this.getListDesigner()
        this.getOneClassofy()
      }
    },
    methods:{
      //根据id查询信息
      getInfo(){
        jewellery.getJewelleryInfo(this.jewelleryId)
              .then(reponse=>{
               this.jewelleryInfo =  reponse.data.jewelleryInfoVo
               //查询所有分类，包含一级和二级
               classofy.getClassofyList()
                      .then(reponse=>{
                        this.classofyOneList =  reponse.data.list

                        for(var i=0;i<this.classofyOneList.length;i++){
                          var oneClassofy = this.classofyOneList[i]

                          if(this.jewelleryInfo.classofyParentId==oneClassofy.id){
                            this.classofyTwoList = oneClassofy.children
                          }
                        }
                      })
                      this.getListDesigner()
            })
      },
        //上传封面成功
        handleAvatarSuccess(res,file){
           this.jewelleryInfo.cover =  res.data.url
        },
        //上传封面之前
        beforeAvatarUpload(file){
            const isJPG = file.type === 'image/jpeg'
            const isLt2M = file.size / 1024 / 1024 < 2
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!')
            }
            return isJPG && isLt2M
        },
        //一级分类改变
        classofyLevelOneChanged(value){
            for(var i=0;i<this.classofyOneList.length;i++){
               var oneClassofy= this.classofyOneList[i]

               if(value === oneClassofy.id){
               this.classofyTwoList= oneClassofy.children
               this.jewelleryInfo.classofyId=''
               }

            }
        },
        //查询所有的一级分类
        getOneClassofy(){
            classofy.getClassofyList()
            .then(response=>{
              this.classofyOneList =  response.data.list
            })
        },
        saveJewellery(){
          if(!this.jewelleryInfo.id){
            this.addJewellery()
          }else{
            this.updateJewellery()
          }
        },
        updateJewellery(){
          jewellery.updateJewelleryInfo(this.jewelleryInfo)
            .then(response=>{
              this.$message({
                    type: 'success',
                    message: '修改珠宝成功!'
                });
                //跳转到最后一步
                this.$router.push({path:'/jewellery/chapter/'+this.jewelleryId})
            })
        },
        addJewellery(){
            jewellery.addJewelleryInfo(this.jewelleryInfo)
            .then(response=>{
                //提示
                this.$message({
                    type: 'success',
                    message: '添加珠宝成功!'
                });
                //跳转到最后一步
                this.$router.push({path:'/jewellery/chapter/'+response.data.jewelleryId})
            })
            
        },
        //查询所有的设计师
        getListDesigner(){
            jewellery.getListDesigner()
            .then(response=>{
                this.designerList =  response.data.items
            })
        }
    }
}
  </script>
  <style scoped>
  .tinymce-container {
    line-height: 29px;
  }
  </style>
<template>
    <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="设计师名称">
        <el-input v-model="designer.name"/>
      </el-form-item>
      <el-form-item label="设计师排序">
        <el-input-number v-model="designer.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="设计师头衔">
        <el-select v-model="designer.level" clearable placeholder="请选择">

          <el-option :value="1" label="高级技师"/>
          <el-option :value="2" label="技师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="设计师资历">
        <el-input v-model="designer.career"/>
      </el-form-item>
      <el-form-item label="设计师简介">
        <el-input v-model="designer.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 设计师头像：TODO -->
      <!-- 设计师头像 -->
<el-form-item label="设计师头像">

      <!-- 头衔缩略图 -->
<pan-thumb :image="designer.avatar"/>
<!-- 文件上传按钮 -->
<el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像</el-button>

<!--
v-show：是否显示上传组件
:key：类似于id，如果一个页面多个图片上传控件，可以做区分
:url：后台上传的url地址
@close：关闭上传组件
@crop-upload-success：上传成功后的回调 -->
<image-cropper
               v-show="imagecropperShow"
               :width="300"
               :height="300"
               :key="imagecropperKey"
               :url="BASE_API+'/bysjoss/fileoss'"
               field="file"
               @close="close"
               @crop-upload-success="cropSuccess"/>

</el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import designer from '../../../api/jewellery/designer'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default{
  components: { ImageCropper, PanThumb },
    data(){
        return{
            designer: {
            name: '',
            sort: 0,
            level: 1,
            career: '',
            intro: '',
            avatar: ''
      },
      imagecropperShow:false,
      imagecropperKey:0,
      BASE_API:process.env.BASE_API,//获取dev.env.js里面的端口号
      saveBtnDisabled: false//保存按钮是否禁用
        }
    },
    created(){
    this.init()
    },
    watch:{//路由监听
      $route(to,from){
        this.init()
      }
    },
    methods:{
      close(){//关闭上传弹窗
        this.imagecropperShow=false
        this.imagecropperKey = this.imagecropperKey+1
      },
      cropSuccess(data){//上传成功
        this.imagecropperShow=false
        this.designer.avatar= data.url
        this.imagecropperKey = this.imagecropperKey+1
      },
        saveOrUpdate() {
      this.saveBtnDisabled = true
      //判断designer是否有id
      if(!this.designer.id){
        //添加设计师
        this.saveData()
      }else{
        //修改设计师
        this.updateDesigner()
      }
    },
    init(){
      if (this.$route.params && this.$route.params.id) {//路径有id值
      const id = this.$route.params.id
      this.getDesignerInfo(id)
    }else{//路径没id
      //清空列表
      this.designer={}
    }
    },
    //修改设计师的方法
    updateDesigner(){
      designer.updateDesignerInfo(this.designer)
      .then(request=>{
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
      }).then(request => {
        //路由跳转
        this.$router.push({ path: '/designer/table' })
      })
    },
    //根据设计师id查询的方法
    getDesignerInfo(id){
      designer.getDesignerInfo(id)
      .then(request =>{
        this.designer=request.data.designer
      })
    },
    //保存
    saveData() {
      designer.addDesigner(this.designer).then(request => {
        return this.$message({
          type: 'success',
          message: '添加设计师成功!'
        })
      }).then(request => {
        //路由跳转
        this.$router.push({ path: '/designer/table' })
      }).catch((request) => {
        this.$message({
          type: 'error',
          message: '添加设计师失败'
        })
      })
    }
    }
}
</script>
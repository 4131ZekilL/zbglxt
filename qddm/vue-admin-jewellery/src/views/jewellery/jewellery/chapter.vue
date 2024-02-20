<template>

    <div class="app-container">
  
      <h2 style="text-align: center;">发布珠宝</h2>
  
      <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
        <el-step title="填写珠宝基本信息"/>
        <el-step title="编辑大纲"/>
        <el-step title="最终发布"/>
      </el-steps>
      <el-button type="text" @click="openChapterDialog()">添加大纲</el-button>
      <!-- 添加和修改大纲表单 -->
      <el-dialog :visible.sync="dialogChapterFormVisible" title="添加大纲">
          <el-form :model="chapter" label-width="120px">
              <el-form-item label="大纲标题">
                  <el-input v-model="chapter.title"/>
              </el-form-item>
              <el-form-item label="大纲排序">
                  <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
              </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
          </div>
      </el-dialog>
      <!-- 添加和修改目录表单 -->
        <el-dialog :visible.sync="dialogVideoFormVisible" title="添加目录">
          <el-form :model="video" label-width="120px">
            <el-form-item label="目录标题">
              <el-input v-model="video.title"/>
            </el-form-item>
            <el-form-item label="目录排序">
              <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
            </el-form-item>
            <el-form-item label="上传视频">
            <el-upload
                  :on-success="handleVodUploadSuccess"
                  :on-remove="handleVodRemove"
                  :before-remove="beforeVodRemove"
                  :on-exceed="handleUploadExceed"
                  :file-list="fileList"
                  :action="BASE_API+'/jewelleryvod/video/uploadAlyiVideo'"
                  :limit="1"
                  class="upload-demo">
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
                <div slot="content">最大支持1G，<br>
                    支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                    GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                    MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                    SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
                <i class="el-icon-question"/>
            </el-tooltip>
            </el-upload>
        </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
            <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
          </div>
        </el-dialog>
          <!-- 大纲 -->
      <ul class="chanpterList">
          <li
              v-for="chapter in chapterVideoList"
              :key="chapter.id">
              <p>
                  {{ chapter.title }}

                  <span class="acts">
                    <el-button style="" type="text" @click="openVideo(chapter.id)">添加目录</el-button>
                      <el-button style="" type="text" @click="openEditChapter(chapter.id)">编辑</el-button>
                      <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
                  </span>
              </p>

              <!-- 视频 -->
              <ul class="chanpterList videoList">
                  <li
                      v-for="video in chapter.children"
                      :key="video.id">
                      <p>{{ video.title }}
                          <span class="acts">
                              <el-button type="text">编辑</el-button>
                              <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                          </span>
                      </p>
                  </li>
              </ul>
          </li>
      </ul>
    <div class="choose">
      <el-button @click="previous">返回修改</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
    </div>
  </div>
  </template>
  
  <script>
import chapter from '@/api/jewellery/chapter'
import video from '@/api/jewellery/video'
  export default {
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        jewelleryId:'',
        publishJewellery:{},
        video:{
            title:'',
            sort:0,
            videoSourceId:'',
            videoOriginalName:''
        },
        chapterVideoList:'',
        fileList: [],//上传文件列表
        BASE_API: process.env.BASE_API, // 接口API地址
        dialogChapterFormVisible:false,
        dialogVideoFormVisible:false,
        chapter:{
          title: '',
          sort: 0
        }
      }
    },
  
    created() {
      if(this.$route.params&& this.$route.params.id){
        this.jewelleryId = this.$route.params.id
      }
      this.getChapterVideo()
    },
  
    methods: {
      //====================================目录操作==============================
      openVideo(chapterId){
        this.dialogVideoFormVisible=true
        this.video.chapterId = chapterId

      },
      removeVideo(id){
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                video.deleteVideo(id)
             }).then(request => { 
                //提示信息
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                })
                //刷新页面
              this.getChapterVideo()
            })
      },
      addVideo(){
        this.video.jewelleryId = this.jewelleryId
        video.addVideo(this.video)
          .then(response=>{
            
             //关闭弹窗
             this.dialogVideoFormVisible = false

            //提示
            this.$message({
                  type: 'success',
                  message: '添加目录成功!'
              });
            //刷新页面
            this.getChapterVideo()
          })
      },
      updateVideo(){

      },
      saveOrUpdateVideo(){
        this.addVideo()
      },
      
      //====================================大纲操作==============================
      removeChapter(chapterId){
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                chapter.deleteChapter(chapterId)
             }).then(request => { 
                //提示信息
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                })
                //刷新页面
              this.getChapterVideo()
            })
      },
      updateChapter(){
        chapter.updateChapter(this.chapter)
            .then(response=>{
              //关闭弹窗
              this.dialogChapterFormVisible = false

              //提示
              this.$message({
                    type: 'success',
                    message: '修改大纲成功!'
                });
              //刷新页面
              this.getChapterVideo()
            })
      },
      openEditChapter(chapterId){
        this.dialogChapterFormVisible=true
        chapter.getChapter(chapterId)
          .then(response=>{
            this.chapter = response.data.chapter
          })
      },
      openChapterDialog(){
        this.dialogChapterFormVisible = true
        this.chapter.title=''
        this.chapter.sort=''
      },
      addChapter(){
        this.chapter.jewelleryId = this.jewelleryId
        chapter.addChapter(this.chapter)
            .then(response=>{
              //关闭弹窗
              this.dialogChapterFormVisible = false

              //提示
              this.$message({
                    type: 'success',
                    message: '添加大纲成功!'
                });
              //刷新页面
              this.getChapterVideo()
            })
      },
      saveOrUpdate(){
        if(!this.chapter.id){
          this.addChapter()
        }else{
          this.updateChapter()
        }
      
      },
      getChapterVideo(){
        chapter.getAllChapterVideo(this.jewelleryId)
              .then(response=>{
                this.chapterVideoList =  response.data.allChapterVideo
              })
      },
      previous() {
        console.log('previous')
        this.$router.push({ path: '/jewellery/info/'+this.jewelleryId})
      },
  
      next() {
            this.$router.push({ path: '/jewellery/publish/'+ this.jewelleryId})           
      },//成功回调
    handleVodUploadSuccess(response, file, fileList) {
         this.video.videoSourceId = response.data.videoId
         this.video.videoOriginalName = file.name
    },
    //视图上传多于一个视频
    handleUploadExceed(files, fileList) {
    this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },
    beforeVodRemove(file,fileList){
      return this.$confirm(`确定移除${file.name}?`)
    },
    handleVodRemove(){
      video.deleteAliyunVod( this.video.videoSourceId)
          .then(response=>{
            //提示
            this.$message({
                    type: 'success',
                    message: '删除视频成功!'
                });
                this.fileList=[]
                this.video.videoSourceId = ''
                this.video.videoOriginalName = ''
          })

    }
    }
  }
  </script>
 <style scoped>
 .chanpterList{
     position: relative;
     list-style: none;
     margin: 0;
     padding: 0;
 }
 .chanpterList li{
   position: relative;
 }
 .chanpterList p{
   float: left;
   font-size: 20px;
   margin: 10px 0;
   padding: 10px;
   height: 70px;
   line-height: 50px;
   width: 100%;
   border: 1px solid #DDD;
 }
 .chanpterList .acts {
     float: right;
     font-size: 14px;
 }
 
 .videoList{
   padding-left: 50px;
 }
 .videoList p{
   float: left;
   font-size: 14px;
   margin: 10px 0;
   padding: 10px;
   height: 50px;
   line-height: 30px;
   width: 100%;
   border: 1px dotted #DDD;
 }
 
 </style>
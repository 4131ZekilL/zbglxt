import request from '@/utils/request'

export default {
//根据珠宝id获取大纲数据
  getAllChapterVideo(jewelleryId) {
    return request({
      url: `/jewelleryservice/chapter/getChapterVideo/`+jewelleryId,
      method: 'get'
    })
  },
  //添加大纲
  addChapter(chapter) {
    return request({
      url: `/jewelleryservice/chapter/addChapter/`,
      method: 'post',
      data:chapter
    })
  },
  //根据id获取大纲
  getChapter(chapterId) {
    return request({
      url: `/jewelleryservice/chapter/getChapterInfo/`+chapterId,
      method: 'get'
    })
  },
  //修改大纲
  updateChapter(chapter) {
    return request({
      url: `/jewelleryservice/chapter/updateChapter`,
      method: 'post',
      data:chapter
    })
  },
  //删除大纲
  deleteChapter(chapterId) {
    return request({
      url: `/jewelleryservice/chapter/`+chapterId,
      method: 'delete'
    })
  },
}
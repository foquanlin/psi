package com.tongyi.es.controller;

public class ElasticSearchController {

    /**
     * @Description 创建Elastic索引
     * @param idxVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     */
//    @RequestMapping(value = "/createIndex",method = RequestMethod.POST)
//    public RestResponse createIndex(@RequestBody IdxVo idxVo){
//        RestResponse response = RestResponse.success();
//        try {
//            //索引不存在，再创建，否则不允许创建
//            if(!baseElasticDao.indexExist(idxVo.getIdxName())){
//                String idxSql = JSONObject.toJSONString(idxVo.getIdxSql());
//                log.warn(" idxName={}, idxSql={}",idxVo.getIdxName(),idxSql);
//                baseElasticDao.createIndex(idxVo.getIdxName(),idxSql);
//            } else{
//                RestResponse.error("索引已经存在，不允许创建");
//            }
//        } catch (Exception e) {
//            RestResponse.error(e.getMessage());
//        }
//        return response;
//    }
//
//    /** 设置分片
//     * @author 林佛权
//     * @See
//     * @param request
//     * @return void
//     * @throws
//     * @since
//     */
//    public void buildSetting(CreateIndexRequest request){
//        request.settings(Settings.builder().put("index.number_of_shards",3)
//                .put("index.number_of_replicas",2));
//
//    /**
//     * @Description 判断索引是否存在；存在-TRUE，否则-FALSE
//     * @param index
//     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
//     * @throws
//     */
//    @RequestMapping(value = "/exist/{index}")
//    public ResponseResult indexExist(@PathVariable(value = "index") String index){
//
//        ResponseResult response = new ResponseResult();
//        try {
//            if(!baseElasticDao.isExistsIndex(index)){
//                log.error("index={},不存在",index);
//                response.setCode(ResponseCode.RESOURCE_NOT_EXIST.getCode());
//                response.setMsg(ResponseCode.RESOURCE_NOT_EXIST.getMsg());
//            } else {
//                response.setMsg(" 索引已经存在, " + index);
//            }
//        } catch (Exception e) {
//            response.setCode(ResponseCode.NETWORK_ERROR.getCode());
//            response.setMsg(" 调用ElasticSearch 失败！");
//            response.setStatus(false);
//        }
//        return response;
//    }
//    /** 删除index
//     * @author 林佛权
//     * @See
//     * @param idxName
//     * @return void
//     * @throws
//     * @since
//     */
//    public void deleteIndex(String idxName) {
//        try {
//            if (!this.indexExist(idxName)) {
//                log.error(" idxName={} 已经存在",idxName);
//                return;
//            }
//            restHighLevelClient.indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

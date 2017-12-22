<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="diy" uri="http://www.mytaglib.com/taglib" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>${posts.PTitle }_${mapSetting.qq1}</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="X-UA-Compatible" content="IE=10; IE=9; IE=8; IE=EDGE">
		

		<!-- 导入外部的样式css -->

		<!-- 公共的css -->
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/newpc/css/topic.css">

		<!-- 不同的分辨率的css -->
		<link rel="stylesheet" type="text/css" href="" id="topic_css">


		<!-- 导入外部JQuery -->
		<script
			src="${pageContext.request.contextPath}/newpc/js/jquery-1.2.6.pack.js"
			type="text/javascript" language="javascript"></script>

		<!-- 导入外部脚本JS -->
		<script src="${pageContext.request.contextPath}/newpc/js/topic.js"
			type="text/javascript" language="javascript"></script>

		<!-- 文本编辑器 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/editor/themes/default/default.css" />
		<script charset="utf-8" src="${pageContext.request.contextPath}/editor/kindeditor-min.js"></script>
		<script charset="utf-8" src="${pageContext.request.contextPath}/editor/lang/zh_CN.js"></script>
		<script type="text/javascript">
			$(function(){
			
				var editor;
				KindEditor.ready(function(K) {
				editor = K.create('textarea[name="txtReplyContent"]', {
					uploadJson : '${pageContext.request.contextPath}/editor/jsp/upload_json.jsp',
					resizeType : 0,
					autoHeightMode : false,
					allowImageRemote : false,
					items : [
						 'emoticons'],
					afterBlur:function(){this.sync();}
					});
				});
				
			});
			
		</script>

	</head>
	
	<jsp:include page="/newpc/top.jsp"></jsp:include>
	
	<body>
		<!-- 帖子详情 --> 
		<div class="topic">
			<div class="topic_left">
			
				<!-- 话题不存在 -->
				<c:if test="${empty posts || posts.shenHeStatus == 0}">
				<div id="divNoTopic" class="tips-con">
					<i style="background-image: url('${pageContext.request.contextPath}/newpc/images/topic/PublicIcon.png');background-position: 0 -75px;"></i>
					此话题已被屏蔽或删除了！
				</div>
				</c:if>
			
				<c:if test="${!empty posts && posts.shenHeStatus != 0}">
				<!-- 圈子 -->
				<div class="topic_detail_top">
					
					<input type="hidden" value="${yungouquan.circle.CId}" id="hiddenCid">
				
					<div class="topic_detail_top_Himg">
						<a href="${pageContext.request.contextPath}/yungouquan.do?p=yungougonggao&id=${yungouquan.circle.CId}" class="">
							<img src="${pageContext.request.contextPath }/newpc/images/${yungouquan.circle.CImage}" border="0" alt="${yungouquan.circle.CName}">
						</a>
					</div>
					
					<div class="topic_detail_top_HC">
						<div class="topic_detail_top_HC_title">
							<h2 ><a id="topicGroupName" href="${pageContext.request.contextPath}/yungouquan.do?p=yungougonggao&id=${yungouquan.circle.CId}" class="">${yungouquan.circle.CName}</a></h2>
							
							<a id="btnJoinGroup" href="javascript:;" class="" <c:if test="${!empty usercircle}">style="display: none;"</c:if> >申请加入</a>
							
							<span class="JoinOut" id="spanJoinOut" 
								<c:if test="${empty usercircle}">style="display: none;"</c:if>>
								<s style="background-position: -33px -46px;background-image: url('${pageContext.request.contextPath}/newpc/images/topic/groupsbg.png')"></s>
								已加入<i>|</i>
								<a id="btnJoinOutGroup" href="javascript:;" class="blue">退出</a>
							</span>
							<span class="topic_return">
								<a href="${pageContext.request.contextPath}/yungouquan.do?p=yungougonggao&id=${yungouquan.circle.CId}" class="gray02">
								&lt;&lt; 
								返回圈子
								</a>
							</span>
						</div>
						<p class="topic_detail_top_HC_other">
							成员：<span class="">${yungouquan.num}</span>
							&nbsp;&nbsp;&nbsp;话题：<span class="">${yungouquan.sum}</span>
							&nbsp;&nbsp;&nbsp;精华：<span class="">${yungouquan.jinghuanum}</span>
							&nbsp;&nbsp;&nbsp;创建时间：<span class=""><fmt:formatDate value="${yungouquan.circle.CCreatedate}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span>
						</p>
					</div>

				</div>
				
				<!-- 帖子 -->
				<div class="topic_detail_content">
					<input type="hidden" value="${posts.PId }" id="hiddenPostId" >
					<!-- 帖子标题 -->
					<div class="topic_detail_content_Ctitle">
						<div class="topic_detail_content_Ctitle_Ctimg">
							<input type="hidden" value="${posts.users.UId}" >
							<a href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${posts.users.UId }" class=""  type="showCard">
								<c:if test="${!empty posts.users.UHeadimage }">
								<img src="${pageContext.request.contextPath}/newpc/gerenImage/${posts.users.UHeadimage}" border="0" alt="">
								</c:if>
								<c:if test="${empty posts.users.UHeadimage }">
							    <img  src="${pageContext.request.contextPath}/logo/4.png" border="0" alt=""></img>
							   	</c:if>
							</a>
						</div>
						
						<div class="topic_detail_content_Ctitle_other">
							<p class="topic_detail_content_Ctitle_other_p">
								<span>${posts.PTitle }</span> 
								<a id="btnEditTopic" href="javascript:;" class="button10" style="display:">
									<s style="display:block; background-image: url('${pageContext.request.contextPath}/newpc/images/topic/groupsbg.png'); background-position: 0 -101px;"></s>
									<s style="display:none; background-image: url('${pageContext.request.contextPath}/newpc/images/topic/groupsbg.png'); background-position: -16px -101px;"></s>
									编辑
								</a> 
								<c:if test="${posts.dingZhiStatus == 1 }">
								<i class="zhiding" style=" background-image: url('${pageContext.request.contextPath}/newpc/images/topic/groupsbg.png'); background-position: -48px -30px;"></i>&nbsp; 
								</c:if>
								<c:if test="${posts.jingHuaStatus == 1 }">
								<i id="JingHua" style="background-image: url('${pageContext.request.contextPath}/newpc/images/topic/groupsbg.png');background-position: -67px -47px;" class="jing"></i>
								</c:if>
							</p>
							<div class="topic_detail_content_Ctitle_div">
							
								<c:choose>
									<c:when test="${!empty posts.users.UUsername }">
									<c:set var="userName" value="${posts.users.UUsername }"></c:set>
									</c:when>
									<c:when test="${!empty posts.users.UPhone }">
							    	<c:set var="userName" value=" ${fn:substring(posts.users.UPhone,0,5) }****${fn:substring(posts.users.UPhone,9,11) } "></c:set>
							    	</c:when>
									<c:when test="${!empty posts.users.UEmail}">
									<c:set var="in" value="${fn:indexOf(posts.users.UEmail,'@')}"></c:set>
									<c:set var="len" value="${fn:length(posts.users.UEmail)}"></c:set>
									<c:set var="userName" value="${fn:substring(posts.users.UEmail,0,2) }****${fn:substring(posts.users.UEmail,in,len) } "></c:set>
									</c:when>
								</c:choose>
							
								<a rel="nofollow" href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${posts.users.UId }" class="blue" uweb="1004519831" type="showCard">${userName }</a> 
								<!-- 
								<span class="topic_detail_content_Ctitle_div_icon">
									<s style="display:none; background-image: url('${pageContext.request.contextPath}/newpc/images/topic/new-class-icon.png'); background-position: -1px 0;"></s>
									<s style="display:none; background-image: url('${pageContext.request.contextPath}/newpc/images/topic/new-class-icon.png'); background-position: -19px 0px;"></s>
									<s style="display:none; background-image: url('${pageContext.request.contextPath}/newpc/images/topic/new-class-icon.png'); background-position: -35px 0;"></s>
									<s style="display:none; background-image: url('${pageContext.request.contextPath}/newpc/images/topic/new-class-icon.png'); background-position: -106px -1px;"></s>
									<s style="display:block; background-image: url('${pageContext.request.contextPath}/newpc/images/topic/new-class-icon.png'); background-position: -106px -1px;"></s>
									云购中将
								</span> 
								-->
								<span class="topic_detail_content_Ctitle_div_time"><fmt:formatDate value="${posts.PDate }" pattern="MM月dd日 HH:mm"></fmt:formatDate></span>&nbsp;&nbsp;
								<span class="topic_detail_content_Ctitle_div_renqi">人气(${posts.PLike })</span>
							</div>
						</div>
					</div>
					
					<!-- 帖子内容 -->
					<div class="topic_detail_content_detail">
						${posts.PDetail }
					
					</div>
					
				</div>
				
				
				<!-- 评论 -->
				<div class="topic_detail_pinglun">
					<!-- 评论顶部 -->
					<div class="topic_detail_pinglun_date">
						<span class="">共 <i id="totalNum" class="">${postReplayNum }</i> 条回复</span> 
						<em><input type="hidden" value=""><a href="javascript:;" class="" id="ahuifu0">回复</a></em>
					</div>
					<c:if test="${fn:length(replymap.list) == 0}">
					<div class="topic_detail_pinglun_con">
						<i style="background-image: url('${pageContext.request.contextPath}/newpc/images/topic/PublicIcon.png');background-position: 0 -75px;"></i>
						暂无回复，快抢沙发吧！
					</div>
					</c:if>
					<!-- 一条评论 -->
					<c:forEach items="${replymap.list}" var="reply">
					
						<c:choose>
							<c:when test="${!empty reply.users.UUsername }">
							<c:set var="userName" value="${reply.users.UUsername }"></c:set>
							</c:when>
							<c:when test="${!empty reply.users.UPhone }">
					    	<c:set var="userName" value=" ${fn:substring(reply.users.UPhone,0,5) }****${fn:substring(reply.users.UPhone,9,11) } "></c:set>
					    	</c:when>
							<c:when test="${!empty reply.users.UEmail}">
							<c:set var="in" value="${fn:indexOf(reply.users.UEmail,'@')}"></c:set>
							<c:set var="len" value="${fn:length(reply.users.UEmail)}"></c:set>
							<c:set var="userName" value="${fn:substring(reply.users.UEmail,0,2) }****${fn:substring(reply.users.UEmail,in,len) } "></c:set>
							</c:when>
						</c:choose>
						
					<div class="topic_detail_pinglun_tiao">
						<div class="topic_detail_pinglun_tiao_head">
							<input type="hidden" value="${reply.users.UId}">
							<a type="showCard" rel="nofollow" href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${reply.users.UId }" class="fl-img">
								<c:if test="${empty reply.users.UHeadimage}">
							    <img alt="" src="${pageContext.request.contextPath}/logo/4.png">
						   		</c:if>
						   		<c:if test="${!empty reply.users.UHeadimage}">
							    <img src="${pageContext.request.contextPath}/newpc/gerenImage/${reply.users.UHeadimage}" >
						   		</c:if>
							</a>
						</div>
						<div class="Comment_con">
							<div class="Comment_User">
								<b>${reply.lou }楼</b>
								<a type="showCard"  href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${reply.users.UId }" class="blue">${userName }</a>
								<c:if test="${!empty reply.repostsre}">对 ${reply.repostsre.lou}楼 说：</c:if>
							</div>
							<div class="Comment_con_summary">
								${reply.prDetail } 
								<span class="gray03"><diy:DateDiff value="${reply.prDate }"></diy:DateDiff></span>
							</div>
							<div class="Comment_con_detail">
								<input type="hidden" value="${reply.prId }">
								<a name="linkReply" floor="${reply.lou }"  uname="${userName }" href="javascript:;" class="blue">回复</a>
							</div>
							<div name="ReplyList" class="qcomment_reply_list_module">
							</div>
						</div>
					</div>
					</c:forEach>
					
					<!-- 页数按钮 -->
					<!--  
					<div id="pagebtn" class="pagebtn">
						<ul class="pagebtn_ul">
							<li class="prev_page">
								<a href="javascript:gotoClick();" onclick="javascript:return CBLFun.gotoPageIndex(11,20);">上一页</a>
							</li>
							<li><a href="javascript:gotoClick();" onclick="javascript:return CBLFun.gotoPageIndex(1,10);">1</a></li>
							<li><a href="javascript:gotoClick();" onclick="javascript:return CBLFun.gotoPageIndex(11,20);">2</a></li>
							<li class="curr_page">3</li><li><a href="javascript:gotoClick();" onclick="javascript:return CBLFun.gotoPageIndex(31,40);">4</a></li>
							<li><a href="javascript:gotoClick();" onclick="javascript:return CBLFun.gotoPageIndex(41,50);">5</a></li>
							<li>…</li>
							<li><a href="javascript:gotoClick();" onclick="javascript:return CBLFun.gotoPageIndex(161,170);">17</a></li>
							<li class="next_page"><a href="javascript:gotoClick();" onclick="javascript:return CBLFun.gotoPageIndex(31,40);">下一页</a></li>
						</ul>
					</div>
					-->
					
					
					<!-- 评论回复 -->
					<div class="Comment_Box_login">
						<input type="hidden" value="${user.UId }" id="hiddenUserId" >
					    <div class="Comment_Box_login_pic">
					    	<c:if test="${empty user.UHeadimage}">
						    <img src="${pageContext.request.contextPath}/logo/4.png" id="imgUserPhoto">
					   		</c:if>
					   		<c:if test="${!empty user.UHeadimage}">
						    <img src="${pageContext.request.contextPath}/newpc/gerenImage/${user.UHeadimage}" id="imgUserPhoto">
					   		</c:if>
					    </div>
					    
	                    <div class="Comment_Box_login_form">
	                    	
	                        <div id="divCommTo" class="Comment_Box_login_form_name" style="display: none"> 
	                        	对 127楼(
	                        	<a href="#" rel="nofollow" class="blue">无力再云购了</a>
	                        	) 说 
	                        	<a id="btnquxiao" style="background-image: url('${pageContext.request.contextPath}/newpc/images/topic/groupsbg.png');background-position: -56px -57px;" name="close" title="取消" href="javascript:void();" onclick="" class="close"></a>
	                        </div>
	                       
	                        <div class="Comment_Box_login_form_text">
	                        	<c:if test="${!empty user}">
	                        	<input type="hidden" id="reid">
	                            <textarea style="display:block;" name="txtReplyContent" id="txtReplyContent" class="textarea01"></textarea>
	                            <div class="Comment_button">
	                            	<div class="Comment_but">
	                            		<a id="btnSubmitMsg" href="javascript:void(0);" class="reply_unbotton">立即发送</a>
	                            	</div>
	                            	<div id="wordNumber" class=""></div>
	                            </div>
	                            </c:if>
	                            <c:if test="${empty user}">
							    <div style="" id="notLogin" class="Comment_login">
								    请您
								    <a id="replyLoginBtn" href="${pageContext.request.contextPath}/newpc/login.jsp" class="blue">登录</a>
								    或
								    <a href="${pageContext.request.contextPath}/regist.do?p=regist" class="blue">注册</a>
								    后再回复评论							
							    </div>
							    </c:if>
	                        </div>
	                        
				        </div>
				        
				    </div>
				    
				</div>
				
				</c:if>
			</div>
			
			<div class="topic_right">
				
			  	<!-- 个人基本信息 -->
				<div class="groups-head">
					<!-- 未登录 -->
					<c:if test="${empty user}">
				    <div class="groups-login" style="">
						<p class="">登录查看您的圈子吧！</p>
						<p class="">没账号？ <span>
							<a href="${pageContext.request.contextPath}/regist.do?p=regist" target="_blank" class="gray01">简单注册 快捷方便！</a>
							</span>
						</p>
						<a id="btnLogin" href="${pageContext.request.contextPath}/newpc/login.jsp" class="">立即登录</a>
					</div>
					</c:if>
					
					
					<!-- 已登录 -->
					<c:if test="${!empty user}">
					<div class="grhead-img">
						<a href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${userInfo.users.UId }" 
						
							<c:choose>
							  	<c:when test="${userInfo.users.UUsername!=null}">
							  		title="${userInfo.users.UUsername}" 
							  	</c:when>
							  	<c:when test="${userInfo.users.UPhone!=null}">
							  		title="${userInfo.users.UPhone}" 
							  	</c:when>
							  	<c:when test="${userInfo.users.UEmail!=null}">
							  		title="${userInfo.users.UEmail}" 
							  	</c:when>
							</c:choose>
						
						
						class="fl-img">
						
						
							<c:if test="${empty userInfo.users.UHeadimage}">
						    <img src="${pageContext.request.contextPath}/logo/4.png" id="imgUserPhoto" border="0" alt="">
					   		</c:if>
					   		<c:if test="${!empty userInfo.users.UHeadimage}">
						    <img src="${pageContext.request.contextPath}/newpc/gerenImage/${userInfo.users.UHeadimage}" border="0" alt="">
					   		</c:if>
						</a>
					</div>
					
					<div class="grhead-info">
					    <p class="grhead-name">
					    	<a rel="nofollow" href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${userInfo.users.UId }" class="gray" 
					    	<c:choose>
							  	<c:when test="${userInfo.users.UUsername!=null}">
							  		title="${userInfo.users.UUsername}" 
							  	</c:when>
							  	<c:when test="${userInfo.users.UPhone!=null}">
							  		title="${userInfo.users.UPhone}" 
							  	</c:when>
							  	<c:when test="${userInfo.users.UEmail!=null}">
							  		title="${userInfo.users.UEmail}" 
							  	</c:when>
							</c:choose>
							
							>
								<c:choose>
								  	<c:when test="${userInfo.users.UUsername!=null}">
								  		${userInfo.users.UUsername}
								  	</c:when>
								  	<c:when test="${userInfo.users.UPhone!=null}">
								  		title="${userInfo.users.UPhone}
								  	</c:when>
								  	<c:when test="${userInfo.users.UEmail!=null}">
								  		${userInfo.users.UEmail}
								  	</c:when>
								</c:choose>
							 </a>
						</p>
					   
					    <p class="grhead-class"><span class="class-icon01">
					    	 <!--  <s style=" background-image: url('${pageContext.request.contextPath}/newpc/images/topic/new-class-icon.png'); background-position: -1px 0;"></s>
					    	云购小将</span>-->&nbsp;</p>
					    
					    <p class="grhead-topic">话题 ${userInfo.huaTiNum }<span class="">|</span>回复 ${userInfo.huiFuNum }</p>
					    <div id="divJoinGroup">
					        <p id="pJoinGroup" class="grhead-join" style="display: block;"> 加入圈子<span id="spJoinGroupNum" class="">${userInfo.jiaQuanNum }</span>个<b></b></p>
				            <div id="divGroupList" class="grhead-joinC" style="display: none;">
							    <div class="grhead-joinCT"> 加入圈子<span class="" id="spJoinGroupNum2">${userInfo.jiaQuanNum }</span>个<b></b></div>
							    <div id="divShowList" class="grhead-joinClist clearfix">
							    	<ul>
							    		<c:forEach items="${userInfo.quanZiList }" var="quanzi">
							    		<li>
							    			<a href="${pageContext.request.contextPath}/yungouquan.do?p=yungougonggao&id=${quanzi.CId}" title="${quanzi.CName }" class="blue">
							    				<img src="${pageContext.request.contextPath }/newpc/images/${quanzi.CImage}" width="50" height="50" border="0" alt=""><i>${quanzi.CName }</i>
							    			</a>
							    		</li>
							    		</c:forEach>
							    	</ul>
							    </div>
						    </div>
					    </div>
				    </div>
				    </c:if>
				    
				</div>
				
				<!-- 间隔层 -->
				<div class="rightJianGe"></div>
				


				<!-- 热门话题 -->
				<div class="zuixinjiaru">
				
					<div class="R-grtit">
						<h3>热门话题</h3>
					</div>
					<c:forEach items="${reMenList}" var="remen">
					<div class="R-list">
						<div class="groups-Rimg">
							<input type="hidden" value="${remen.posts.users.UId}">
							<a type="showCard" href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${remen.posts.users.UId }" class="fl-img">
								<c:if test="${!empty remen.posts.users.UHeadimage }">
								<img src="${pageContext.request.contextPath}/newpc/gerenImage/${remen.posts.users.UHeadimage}" border="0" alt="">
								</c:if>
								<c:if test="${empty remen.posts.users.UHeadimage }">
							    <img  src="${pageContext.request.contextPath}/logo/4.png" border="0" alt=""></img>
							   	</c:if>
							</a>
						</div>
						<div class="groups-Rinfo">
							<p class="groups-ht" id="groups-Rinfo-p1">
								<a href="${pageContext.request.contextPath}/yungouquan.do?p=showpost&postid=${remen.posts.PId }" title="${remen.posts.PTitle}" class="gray">
									${remen.posts.PTitle}
								</a>
							</p>
							<p class="groups-c">${remen.posts.circle.CName}<span class=""> | </span>${remen.sum}条回复</p>
						</div>
					</div>
					</c:forEach>
				
				</div>
				
				
				
				<!-- 间隔层 -->
				<div class="rightJianGe"></div>

			
				<!-- 活跃成员 -->
				<div class="zuixinjiaru" id="huoyueheight">
					<div class="R-grtit">
						<h3>活跃成员</h3>
					</div>
					
					<div class="zuixinjiaru_list">
						<ul>
							<c:forEach items="${huoyuelist}" var="post">
							<li>
								<input type="hidden" value="${post.users.UId}">
								<a type="showCard" href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${post.users.UId }" class="">
									<c:if test="${!empty post.users.UHeadimage }">
									<img src="${pageContext.request.contextPath}/newpc/gerenImage/${post.users.UHeadimage}" width="50" height="50" border="0" alt="">
									</c:if>
									<c:if test="${empty post.users.UHeadimage }">
								    <img  src="${pageContext.request.contextPath}/logo/4.png" border="0" alt=""></img>
								   	</c:if>
									<i>
										<c:choose>
											<c:when test="${!empty post.users.UUsername }">
											<c:set var="userName" value="${post.users.UUsername }"></c:set>
											</c:when>
											<c:when test="${!empty post.users.UPhone }">
									    	<c:set var="userName" value=" ${fn:substring(post.users.UPhone,0,5) }****${fn:substring(post.users.UPhone,9,11) } "></c:set>
									    	</c:when>
											<c:when test="${!empty post.users.UEmail}">
											<c:set var="in" value="${fn:indexOf(post.users.UEmail,'@')}"></c:set>
											<c:set var="len" value="${fn:length(post.users.UEmail)}"></c:set>
											<c:set var="userName" value="${fn:substring(post.users.UEmail,0,2) }****${fn:substring(post.users.UEmail,in,len) } "></c:set>
											</c:when>
										</c:choose>
										${userName }
									</i>
								</a>
							</li>
							</c:forEach>
						</ul>
					</div>
				
				</div>
				
				<!-- 间隔层 -->
				<div class="rightJianGe"></div>
				


				<!-- 圈子动态 -->
				<div class="zuixinjiaru">
				
					<div class="R-grtit">
						<h3>圈子动态</h3>
					</div>
					<c:forEach items="${dongtailist}" var="postsReply">
					<div class="R-list">
						<div class="groups-Rimg">
							<input type="hidden" value="${postsReply.users.UId}">
							<a  type="showCard" href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${postsReply.users.UId }" class="fl-img">
								<c:if test="${!empty postsReply.users.UHeadimage }">
								<img src="${pageContext.request.contextPath}/newpc/gerenImage/${postsReply.users.UHeadimage}" border="0" alt="">
								</c:if>
								<c:if test="${empty postsReply.users.UHeadimage }">
							    <img  src="${pageContext.request.contextPath}/logo/4.png" border="0" alt=""></img>
							   	</c:if>
							</a>
						</div>
						<div class="groups-Rinfo">
							<p class="groups-t">
								<a type="showCard" rel="nofollow" href="${pageContext.request.contextPath }/homepage.do?p=homepage&uid=${postsReply.users.UId }" class="blue">
								<c:choose>
									<c:when test="${!empty postsReply.users.UUsername }">
									<c:set var="userName" value="${postsReply.users.UUsername }"></c:set>
									</c:when>
									<c:when test="${!empty postsReply.users.UPhone }">
							    	<c:set var="userName" value=" ${fn:substring(postsReply.users.UPhone,0,5) }****${fn:substring(postsReply.users.UPhone,9,11) } "></c:set>
							    	</c:when>
									<c:when test="${!empty postsReply.users.UEmail}">
									<c:set var="in" value="${fn:indexOf(postsReply.users.UEmail,'@')}"></c:set>
									<c:set var="len" value="${fn:length(postsReply.users.UEmail)}"></c:set>
									<c:set var="userName" value="${fn:substring(postsReply.users.UEmail,0,2) }****${fn:substring(postsReply.users.UEmail,in,len) } "></c:set>
									</c:when>
								</c:choose>
								${userName }
								</a> 
								<fmt:formatDate value="${postsReply.prDate }" pattern="MM月dd日 HH:mm"></fmt:formatDate>回复话题 
								<a href="${pageContext.request.contextPath}/yungouquan.do?p=showpost&postid=${postsReply.posts.PId }" class="gray">${postsReply.posts.PTitle }</a>
							</p>
						</div>
					</div>
					</c:forEach>
				
				</div>
				
				
			</div>
		</div>

		
		
		
		<!-- 用户卡片 -->
		<div id="divUserCard" class="Member_Pop" style="top: 10px; left:10px; z-index: 1024; display: none;">
			<input id="UserCardId" type="hidden" value="">
			<div class="Member_Pop_img">
				<a href="#">
					<img id="Member_Pop_img_pic" src="${pageContext.request.contextPath}/logo/4.png" border="0" alt="">
				</a>
			</div>
			<div class="Member_Pop_info">
				<p class="Member_Pop_name">
					<a id="UserCardName" href="#" class="blue" rel="nofollow">${mapSetting.qq1}</a>
				</p>
				<p class="Member_Pop_class">
					<span class="class-icon07">
					<!-- 
					<s style="display:block; background-image: url('${pageContext.request.contextPath}/newpc/images/topic/new-class-icon.png'); background-position: -106px -1px;"></s>
					云购官方
					 -->&nbsp;</span>
				</p>
				<p id="signature" class="Member_Pop_intro">这家伙很懒，什么也没留下。</p>
			</div>
			<div class="Member_Pop_but">
				<a href="javascript:void();"  class="Member_Pop_letter">
					<i style="background-image:url('${pageContext.request.contextPath}/newpc/images/topic/star-icon.png'); background-position: -11px -53px;"></i>
					发私信
				</a>
				<a href="javascript:void();" class="Member_Pop_friend">
					<s style="background-image:url('${pageContext.request.contextPath}/newpc/images/topic/star-icon.png');  background-position: 0 -53px;"></s>
					加好友
				</a>
			</div>
		</div>
		

	</body>
	<jsp:include page="/newpc/bottom.jsp"></jsp:include>
</html>

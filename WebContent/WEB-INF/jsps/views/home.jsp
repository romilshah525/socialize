<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Socialize - Quarantine Buster </title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta property="og:title" content="" />
	<meta property="og:url" content="" />
	<meta property="og:description" content="" />
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="icon" href="resources/assets/img/logo.html">
	<link rel="apple-touch-icon" href="resources/img/favicons/apple-touch-icon.html">
	<link rel="apple-touch-icon" sizes="72x72" href="resources/img/favicons/apple-touch-icon-72x72.html">
	<link rel="apple-touch-icon" sizes="114x114" href="resources/img/favicons/apple-touch-icon-114x114.html">
	<link type="text/css" href="resources/assets/css/demos/photo.css" rel="stylesheet" />
	<script src="resources/assets/js/modernizr-custom.html"></script>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>

<style>
.cardbox-like a:hover {background-color: #ddd;}
</style>

</head>

<body>
	<header class="tr-header">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
						data-target="#navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand"><i class="fab fa-instagram"></i> Socialize</a>
				</div>
				<div class="navbar-left">
					<div class="collapse navbar-collapse" id="navbar-collapse">
						<ul class="nav navbar-nav">
						</ul>
					</div>
				</div>
				<div class="navbar-right">
					<ul class="nav navbar-nav">
						<li class="dropdown notification-list">
							<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
								aria-haspopup="false" aria-expanded="false">
								<i class="fa fa-bell noti-icon"></i>
								<span class="badge badge-danger badge-pill noti-icon-badge"> ${notificationsForHome.size()} </span>
							</a>
							<div class="dropdown-menu dropdown-menu-right dropdown-lg">

								<div class="dropdown-item noti-title">
									<h6 class="m-0">
										<span class="pull-right">
											<a href="#" class="text-dark"><small>Clear All</small></a>
										</span>Notification
									</h6>
								</div>

								<div class="slimScrollDiv"
									style="position: relative; overflow: hidden; width: auto; height: 416.983px;">
									<div class="slimscroll"
										style="max-height: 230px; overflow: hidden; width: auto; height: 416.983px;">
										<div id="Slim">
											
											<c:forEach items="${notificationsForHome }" var="notification">
												
												<c:if test="${notification.getTypeOfNotification() == 'LIKE' }">
												
													<a href="javascript:void(0);" class="dropdown-item notify-item">
														<div class="notify-icon bg-success"><i class="fa fa-heart"></i></div>
														<p class="notify-details">${ userService.returnNameOfUser( notification.getNotificationFromUserId() ) } liked your photo.
															<!-- <small class="text-muted">3 days ago</small> -->
														</p>
													</a>
													
												</c:if>
												
												<c:if test="${notification.getTypeOfNotification() == 'COMMENT' }">
													
													<a href="javascript:void(0);" class="dropdown-item notify-item">
														<div class="notify-icon bg-success"><i class="fa fa-comment"></i></div>
														<p class="notify-details">${ userService.returnNameOfUser( notification.getNotificationFromUserId() ) } commented on your photo<small
																class="text-muted">1 min ago</small></p>
													</a>
											
												</c:if>
												
												<c:if test="${notification.getTypeOfNotification() == 'REQUEST' }">
													
													<a href="javascript:void(0);" class="dropdown-item notify-item">
														<div class="notify-icon bg-success"><i class="fa fa-user-plus"></i>
														</div>
														<p class="notify-details">${ userService.returnNameOfUser( notification.getNotificationFromUserId() ) } followed you.<small
																class="text-muted">5 hours ago</small></p>
													</a>
													
												</c:if>
												
												<c:if test="${notification.getTypeOfNotification() == 'STATUS' }">
													
													<a href="javascript:void(0);" class="dropdown-item notify-item">
														<div class="notify-icon bg-success"><i class="fa fa-align-left"></i> 
														</div>
														<p class="notify-details">${ userService.returnNameOfUser( notification.getNotificationFromUserId() ) } added a story.<small
																class="text-muted">5 hours ago</small></p>
													</a>
													
												</c:if>
											 
											
											</c:forEach>
												
											
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<div class="notify-icon bg-success"><i class="fa fa-comment"></i></div>
												<p class="notify-details">Caleb Flakelar commented on Admin<small
														class="text-muted">1 min ago</small></p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<div class="notify-icon bg-success"><i class="fa fa-user-plus"></i>
												</div>
												<p class="notify-details">Grace Flake followed you.<small
														class="text-muted">5 hours ago</small></p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<div class="notify-icon bg-success"><i class="fa fa-heart"></i></div>
												<p class="notify-details">Carlos Crouch liked your photo.<small
														class="text-muted">3 days ago</small></p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<div class="notify-icon bg-success"><i class="fa fa-comment"></i></div>
												<p class="notify-details">Caleb Flakelar commented on Admin<small
														class="text-muted">4 days ago</small></p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<div class="notify-icon bg-success"><i class="fa fa-user-plus"></i>
												</div>
												<p class="notify-details">Maureen Hilda followed you.<small
														class="text-muted">7 days ago</small></p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<div class="notify-icon bg-success"><i class="fa fa-heart"></i></div>
												<p class="notify-details">Carlos Crouch liked your photo.<small
														class="text-muted">13 days ago</small></p>
											</a>
											
										</div>
										<div class="slimScrollBar"
											style="background: rgb(158, 165, 171) none repeat scroll 0% 0%; width: 8px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;">
										</div>
										<div class="slimScrollRail"
											style="width: 8px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;">
										</div>
									</div>
								</div>
								<a href="notifications?userId=${userId}" class="dropdown-item text-center notify-all">
									View all <i class="fa fa-arrow-right"></i>
								</a>
							</div>
						</li>

						<li class="dropdown notification-list">
							<a class="nav-link dropdown-toggle arrow-none waves-effect" data-toggle="dropdown" href="#"
								role="button" aria-haspopup="false" aria-expanded="false">
								<i class="fa fa-envelope noti-icon"></i>
								<span class="badge badge-success badge-pill noti-icon-badge">${homeFriendsMessages.size() }</span>
							</a>
							<div class="dropdown-menu dropdown-menu-right dropdown-lg dropdown-new">
								<div class="dropdown-item noti-title">
									<h6 class="m-0">
										<span class="float-right">
											<a href="#" class="text-dark"><small>Clear All</small></a>
										</span>Chat
									</h6>
								</div>

								<div class="slimScrollDiv"
									style="position: relative; overflow: hidden; width: auto; height: 416.983px;">
									<div class="slimscroll"
										style="max-height: 230px; overflow: hidden; width: auto; height: 416.983px;">
										<div id="Slim2">
										
											<c:forEach items="${homeFriendsMessages }" var="homeMessage">
												<c:set var="messageOfUser" value="${userService.getUserWithId( homeMessage.getSenderId() )  }"></c:set>
												<a href="javascript:void(0);" class="dropdown-item notify-item nav-user">
													<div class="notify-icon"><img src="data:image/jpg;base64,${messageOfUser.getProfileImage()}"
															class="img-responsive img-circle" alt=""> </div>
													<p class="notify-details">${messageOfUser.getName() }</p>
													<p class="text-muted font-13 mb-0 user-msg">
													
														<c:if test="${homeMessage.getMediaType() eq null }">
															${homeMessage.getMessage()}
														</c:if>
														<c:if test="${homeMessage.getMediaType() eq 'PICTURE' }">
															<!-- <i class="material-icons"></i> --> you have received Picture..
														</c:if>
														<c:if test="${homeMessage.getMediaType() eq 'VIDEO' }">
															<!-- <i class="fa fa-video-camera"> --> you have received Video..
														</c:if>
														<c:if test="${homeMessage.getMediaType() eq 'DOCUMENT' }">
															<!-- <i class="fa fa-file-text"> </i> --> you have received Document..
														</c:if>
													
													</p>
												</a>
											</c:forEach>
											
										</div>
										<div class="slimScrollBar"
											style="background: rgb(158, 165, 171) none repeat scroll 0% 0%; width: 8px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;">
										</div>
										<div class="slimScrollRail"
											style="width: 8px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;">
										</div>
									</div>
								</div>
								<a href="chat?userId=${userId}" class="dropdown-item text-center notify-all">
									View all <i class="fa fa-arrow-right"></i>
								</a>
							</div>
						</li>
						<li class="dropdown mega-avatar">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
								<span class="avatar w-32"><img src="data:image/jpg;base64,${user.getProfileImage()}"
										class="img-resonsive img-circle" width="25" height="25" alt="..."></span>

								<span class="hidden-xs">
									${user.getName() }
								</span>
							</a>
							<div class="dropdown-menu w dropdown-menu-scale pull-right">
								<a class="dropdown-item" href="stories?userId=${userId}&statusId=0&prev=0&next=14"><span>New Story</span></a>
								<a class="dropdown-item" href="profile?userId=${userId}&profileId=${userId}"><span>Profile</span></a>
								<a class="dropdown-item" href="#">Sign out</a>
							</div>
						</li>

					</ul>
				</div>
			</div>
		</nav>
	</header>
	<section class="nav-sec">
		<div class="d-flex justify-content-between">
			<div class="p-2 nav-icon-lg mint-green">
				<a class="nav-icon" href="home?userId=${userId}&postId=0&prev=0&next=1"><em class="fa fa-home"></em>
					<span>Home</span>
				</a>
			</div>
			<div class="p-2 nav-icon-lg clean-black">
				<a class="nav-icon" href="exploreSection?userId=${userId}"><em class="fa fa-crosshairs"></em>
					<span>Explore</span>
				</a>
			</div>
			<div class="p-2 nav-icon-lg dark-black">
				<a class="nav-icon" href="upload?userId=${userId}"><em class="fab fa-instagram"></em>
					<span>Upload</span>
				</a>
			</div>
			<div class="p-2 nav-icon-lg clean-black">
				<a class="nav-icon" href="stories?userId=${userId}&statusId=0&prev=0&next=1"><em class="fa fa-align-left"></em>
					<span>Stories</span>
				</a>
			</div>
			<div class="p-2 nav-icon-lg dark-black">
				<a class="nav-icon" href="profile?userId=${userId}&profileId=${userId}"><em class="fa fa-user"></em>
					<span>Profile</span>
				</a>
			</div>
		</div>
	</section>
	<section class="newsfeed">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-3">
					<c:forEach items="${homeStatus }" var="status">
						
						<c:set var="statusOfUser" value="${userService.getUserWithId( status.getUserId() )  }"/>
							
							<a href="#">
								<div class="storybox" style="background: linear-gradient( rgba(34,34,34,0.78), rgba(34,34,34,0.78)), url('data:image/jpg;base64,${ status.getStatus()}') no-repeat;
				          background-size: cover;
		                  background-position: center center;
		                  -webkit-background-size: cover;
		                  -moz-background-size: cover;
		                  -o-background-size: cover;">
									<div class="story-body text-center">
										<div class="">
											<a href="#myStatusModal${status.getId() }" data-toggle="modal">
												<img class="img-circle" src="data:image/jpg;base64,${ statusOfUser.getProfileImage() }" alt="user">
											</a>
										</div>
										<h4>${ statusOfUser.getName() }</h4>
										<p>${ status.getTime() }</p>
									</div>
								</div>
							</a>
							
								
						
						<div id="myStatusModal${status.getId() }" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-body">
										<div class="row">
											<div class="col-md-8 modal-image">
												<img class="img-responsive" src="data:image/jpg;base64,${status.getStatus()}" alt="Image" />
											</div>
											<div class="col-md-4 modal-meta">
												<div class="modal-meta-top">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														<span aria-hidden="true"> X </span><span class="sr-only">Close</span>
													</button>
													<div class="img-poster clearfix">
														<a href="#">
															<img class="img-responsive img-circle" src="data:image/jpg;base64,${statusOfUser.getProfileImage()}" alt="Image" />
														</a>
														<strong><a href="profile?userId=${userId}&profileId=${status.getUserId()}">${statusOfUser.getName() }</a></strong>
														<span>${status.getTime() }</span><br />
														<a href="#" class="kafe kafe-btn-mint-small"><i class="fa fa-check-square"></i>
														Following</a>
													</div>
													</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
							
							
					</c:forEach>
					<div class="trending-box hidden-xs hidden-md">
						<div class="row">
							<div class="col-lg-12">
								<a href="stories?userId=${userId}&statusId=0&prev=0&next=1">
									<h4>More stories</h4>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					
					<c:forEach var="post" items="${homePosts}">
						
						<c:set var="postOfUser" value="${userService.getUserWithId( post.getUserId() )  }"></c:set>
							
							<div class="cardbox">
								<div class="cardbox-heading">
									<div class="dropdown pull-right">
										<button class="btn btn-secondary btn-flat btn-flat-icon" type="button"
											data-toggle="dropdown" aria-expanded="false">
											<em class="fa fa-ellipsis-h"></em>
										</button>
										<div class="dropdown-menu dropdown-scale dropdown-menu-right" role="menu"
											style="position: absolute; transform: translate3d(-136px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
											<a class="dropdown-item" href="#">Hide post</a>
											<a class="dropdown-item" href="#">Stop following</a>
											<a class="dropdown-item" href="#">Report</a>
										</div>
									</div>
									<div class="media m-0">
										<div class="d-flex mr-3">
											<a href="#"><img class="img-responsive img-circle" src="data:image/jpg;base64,${ postOfUser.getProfileImage() }"
													alt="User"></a>
										</div>
										<div class="media-body">
											<a href="profile?userId=${userId}&profileId=${postOfUser.getId()}">
												<p class="m-0">${ postOfUser.getName() }</p>
											</a>
											<small><span> ${post.getTime() } </span></small>
										</div>
									</div>
								</div>
								<div class="cardbox-item">
									<a href="seePost?userId=${userId}&postId=${post.getId()}&profileId=${postOfUser.getId()}" data-toggle="modal"><%-- <a href="#myModal${post.getId() }" data-toggle="modal"> --%>
										<img class="img-responsive" src="data:image/jpg;base64,${ post.getPost() }" alt="MaterialImg">
									</a>
								</div>
								<div class="cardbox-base">
									<ul>
										<c:forEach var="postLike" items="${post.getLikes() }">  
											<li><a href="#"><img src="data:image/jpg;base64,${ userService.getProfileImageOfUser(  postLike.getUserId() ) }" class="img-responsive img-circle"
														alt="User"></a></li>
										</c:forEach>
									</ul>
								</div>
								<div class="cardbox-like">
									<ul>
									
										<c:set var="liked" value="0"/>
										<c:forEach items="${post.getLikes()}" var="l">
											<c:if test="${l.getUserId() eq user.getId() }">
												<c:set var="liked" value="1"/>
											</c:if>
										</c:forEach>
										
										<c:if test="${liked eq 0}">
											<li><a href="likePost?userId=${userId}&postId=${post.getId()}&prev=${prev}&next=${next}"><i class="fa fa-thumbs-up"></i></a><span> ${ ( post.getLikes() ).size() }</span></li>
										</c:if>
										
										<c:if test="${liked eq 1}">
											<li><a href="dislikePost?userId=${userId}&postId=${post.getId()}&prev=${prev}&next=${next}"><i class="fa fa-thumbs-down"></i></a><span> ${ ( post.getLikes() ).size() }</span></li>
										</c:if>
										
										<li><a href="#" title="" class="com"><i class="fa fa-comments"></i></a> <span
												class="span-last"> ${ ( post.getComments() ).size() }</span></li>
									</ul>
								</div>
							</div>
					
					</c:forEach>
				
					<div class="cardbox-like">
						<ul>
							<c:if test="${prev > 0 }">
								<li><a href="home?userId=${userId }&postId=0&prev=${prev-1}&next=${next-1}"> <span style="font-size: 50px;"> ${prev } &laquo;</span> </a></li>
							</c:if>
							<c:if test="${homePosts.size() == 5 }">
								<li><a href="home?userId=${userId }&postId=0&prev=${prev+1}&next=${next+1}"> <span style="font-size: 50px;"> &raquo; ${next+1 }</span> </a> </li>
							</c:if>
						</ul>
					</div>
				
				</div>
				
				<div class="col-lg-3">
					<div class="trending-box">
						<div class="row">
							<div class="col-lg-12">
								<h4>Recommended Friends</h4>
							</div>
						</div>
					</div>
					<div class="suggestion-box full-width">
						<div class="suggestions-list">
						
							<c:forEach items="${homeFriendSuggestion}" var="suggestedFriend">
								
								<div class="suggestion-body">
									<img class="img-responsive img-circle" src="data:image/jpg;base64,${suggestedFriend.getProfileImage() }" alt="Image">
									<div class="name-box">
										<a href="profile?userId=${userId}&profileId=${suggestedFriend.getId()}">
											<h4>${suggestedFriend.getName() }</h4>
										</a>
										<span>${ suggestedFriend.getEmail() }</span>
									</div>
									<span>
										<a href="followFromHome?userId=${userId}&postId=0&prev=${prev}&next=${next}&requestPersonId=${suggestedFriend.getId()}">
											<i class="fa fa-plus"></i>
										</a>
									</span>
								</div>
							</c:forEach>

						</div>
					</div>
					<div class="trending-box">
						<div class="row">
							<div class="col-lg-12">
								<h4>Trending Photos</h4>
							</div>
						</div>
					</div>
					<div class="trending-box">
						<div class="row">
							<div class="col-lg-6">
								<a href="#"><img src="resources/assets/img/posts/17.jpg" class="img-responsive" alt="Image" /></a>
							</div>
							<div class="col-lg-6">
								<a href="#"><img src="resources/assets/img/posts/12.jpg" class="img-responsive" alt="Image" /></a>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<a href="#"><img src="resources/assets/img/posts/17.jpg" class="img-responsive" alt="Image" /></a>
							</div>
							<div class="col-lg-6">
								<a href="#"><img src="resources/assets/img/posts/12.jpg" class="img-responsive" alt="Image" /></a>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<a href="#"><img src="resources/assets/img/posts/17.jpg" class="img-responsive" alt="Image" /></a>
							</div>
							<div class="col-lg-6">
								<a href="#"><img src="resources/assets/img/posts/12.jpg" class="img-responsive" alt="Image" /></a>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<a href="#"><img src="resources/assets/img/posts/17.jpg" class="img-responsive" alt="Image" /></a>
							</div>
							<div class="col-lg-6">
								<a href="#"><img src="resources/assets/img/posts/12.jpg" class="img-responsive" alt="Image" /></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!--<c:forEach var="post" items="${homePosts}">
						
		<c:set var="postOfUser" value="${userService.getUserWithId( post.getUserId() )  }"></c:set>
		
		<div id="myModal${post.getId() }" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div class="row">
							<div class="col-md-8 modal-image">
								<img class="img-responsive" src="data:image/jpg;base64,${post.getPost()}" alt="Image" />
							</div>
							<div class="col-md-4 modal-meta">
								<div class="modal-meta-top">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										<span aria-hidden="true"> X </span><span class="sr-only">Close</span>
									</button>
									<div class="img-poster clearfix">
										<a href="#"><img class="img-responsive img-circle" src="data:image/jpg;base64,${postOfUser.getProfileImage()}"
												alt="Image" /></a>
										<strong><a href="#">${postOfUser.getName() }</a></strong>
										<span>${post.getTime() }</span><br />
										<a href="#" class="kafe kafe-btn-mint-small"><i class="fa fa-check-square"></i>
											Following</a>
									</div>
									<ul class="img-comment-list">
										
										<c:forEach var="postComment" items="${post.getComments() }"> 
										
											<c:set var="commentUser" value="${userService.getUserWithId( postComment.getUserId() ) }"></c:set>
											<li>
												<div class="comment-img">
													<img src="data:image/jpg;base64,${commentUser.getProfileImage()}" class="img-responsive img-circle"
														alt="Image" />
												</div>
												<div class="comment-text">
													<strong><a href="#">${commentUser.getName() }</a></strong>
													<p>${postComment.getComment()}</p> <span class="date sub-text">${postComment.getTime() }</span>
													<span class="date sub-text">${postComment.getDate()}</span>
													
												</div>
											</li>
										
										</c:forEach>
										
									</ul>
									<div class="modal-meta-bottom">
										<ul>
											<li><a class="modal-like" href="#"><i class="fa fa-heart"></i></a><span
													class="modal-one"> ${ (post.getLikes()).size() }</span> |
												<a class="modal-comment" href="#"><i class="fa fa-comments"></i></a><span>
													${(post.getComments()).size() }</span> </li>
											<li>
												<span class="thumb-xs">
													<img class="img-responsive img-circle"
														src="data:image/jpg;base64,${user.getProfileImage() }" alt="Image">
												</span>
												<div class="comment-body">
													<input class="form-control input-sm" type="text"
														placeholder="Write your comment...">
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	</c:forEach>
	-->
	<script src="resources/assets/js/jquery.min.js"></script>
	<script src="resources/assets/js/bootstrap.min.js"></script>
	<script src="resources/assets/js/base.js"></script>
	<script src="resources/assets/plugins/slimscroll/jquery.slimscroll.js"></script>
	<script>
		$('#Slim,#Slim2').slimScroll({
			height: "auto",
			position: 'right',
			railVisible: true,
			alwaysVisible: true,
			size: "8px",
		});		
	</script>
</body>

</html>
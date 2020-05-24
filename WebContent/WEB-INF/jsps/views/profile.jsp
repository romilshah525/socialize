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
	

	<link rel="icon" href="resources/assets/img/logo.html">
	<link rel="apple-touch-icon" href="resources/img/favicons/apple-touch-icon.html">
	<link rel="apple-touch-icon" sizes="72x72" href="resources/img/favicons/apple-touch-icon-72x72.html">
	<link rel="apple-touch-icon" sizes="114x114" href="resources/img/favicons/apple-touch-icon-114x114.html">
	<link type="text/css" href="resources/assets/css/demos/photo.css" rel="stylesheet" />
	<script src="resources/assets/js/modernizr-custom.html"></script>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
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
								<span class="badge badge-danger badge-pill noti-icon-badge">${notificationsForHome.size()}</span>
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
											
											<a href="javascript:void(0);" class="dropdown-item notify-item nav-user">
												<div class="notify-icon"><img src="resources/assets/img/users/1.jpg"
														class="img-responsive img-circle" alt=""> </div>
												<p class="notify-details">Cristina Pride</p>
												<p class="text-muted font-13 mb-0 user-msg">Hi, How are you? What about
													our next meeting</p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item nav-user">
												<div class="notify-icon"><img src="resources/assets/img/users/2.jpg"
														class="img-responsive img-circle" alt=""> </div>
												<p class="notify-details">Sam Garret</p>
												<p class="text-muted font-13 mb-0 user-msg">Yeah everything is fine</p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item nav-user">
												<div class="notify-icon"><img src="resources/assets/img/users/3.jpg"
														class="img-responsive img-circle" alt=""> </div>
												<p class="notify-details">Karen Robinson</p>
												<p class="text-muted font-13 mb-0 user-msg">Wow that's great</p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item nav-user">
												<div class="notify-icon"><img src="resources/assets/img/users/4.jpg"
														class="img-responsive img-circle" alt=""> </div>
												<p class="notify-details">Sherry Marshall</p>
												<p class="text-muted font-13 mb-0 user-msg">Hi, How are you? What about
													our next meeting</p>
											</a>
											<a href="javascript:void(0);" class="dropdown-item notify-item nav-user">
												<div class="notify-icon"><img src="resources/assets/img/users/5.jpg"
														class="img-responsive img-circle" alt=""> </div>
												<p class="notify-details">Shawn Millard</p>
												<p class="text-muted font-13 mb-0 user-msg">Yeah everything is fine</p>
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
								<a href="./whatsapp_chatscreen.html" class="dropdown-item text-center notify-all">
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
			<div class="p-2 nav-icon-lg dark-black">
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
			<c:if test="${userId eq profileId }">
				<div class="p-2 nav-icon-lg mint-green">
					<a class="nav-icon" href="profile?userId=${userId}&profile=${userId}"><em class="fa fa-user"></em>
						<span>Profile</span>
					</a>
				</div>
			</c:if>
			<c:if test="${userId ne profileId }">
				<div class="p-2 nav-icon-lg dark-black">
					<a class="nav-icon" href="profile?userId=${userId}&profileId=${userId}"><em class="fa fa-user"></em>
						<span>Profile</span>
					</a>
				</div>
			</c:if>
			
		</div>
	</section>
	<section class="profile">
		<div class="container-fluid">
			<div class="row">

				<div class="col-lg-3">
					<div class="profilebox hidden-xs hidden-sm" style="background: linear-gradient( rgba(34,34,34,0.45), rgba(34,34,34,0.45)), url('resources/assets/img/posts/4.jpg') no-repeat;
		          background-size: cover;
                  background-position: center center;
                  -webkit-background-size: cover;
                  -moz-background-size: cover;
                  -o-background-size: cover;">
					</div>
					<div class="profilebox hidden-xs hidden-sm" style="background: linear-gradient( rgba(34,34,34,0.45), rgba(34,34,34,0.45)), url('resources/assets/img/posts/1.jpg') no-repeat;
		          background-size: cover;
                  background-position: center center;
                  -webkit-background-size: cover;
                  -moz-background-size: cover;
                  -o-background-size: cover;">
					</div>
				</div>
				<div class="col-lg-6">
					<div class="profilebox-large hidden-xs hidden-sm" style="background: linear-gradient( rgba(34,34,34,0.45), rgba(34,34,34,0.45)), url('resources/assets/img/posts/9.jpg') no-repeat;
		          background-size: cover;
                  background-position: center center;
                  -webkit-background-size: cover;
                  -moz-background-size: cover;
                  -o-background-size: cover;">
					</div>
				</div>
				<div class="col-lg-3">
					<div class="profilebox hidden-xs hidden-sm" style="background: linear-gradient( rgba(34,34,34,0.45), rgba(34,34,34,0.45)), url('resources/assets/img/posts/11.jpg') no-repeat;
		          background-size: cover;
                  background-position: center center;
                  -webkit-background-size: cover;
                  -moz-background-size: cover;
                  -o-background-size: cover;">
					</div>
					<c:forEach var="userPost" items="${profileUser.getPosts()}" end="0">
						<div class="profilebox" style="background: linear-gradient( rgba(34,34,34,0.6), rgba(34,34,34,0.6)), url('data:image/jpg;base64,${userPost.getPost()}') no-repeat;
			          background-size: cover;
	                  background-position: center center;
	                  -webkit-background-size: cover;
	                  -moz-background-size: cover;
	                  -o-background-size: cover;">
						</div>
					</c:forEach>
				</div>

			</div>
		</div>
	</section>
	<section class="user-profile">
		<div class="container-fluid">
			<div class="row">

				<div class="col-lg-12">
					<div class="post-content">
						<div class="author-post text-center">
							<a href="#"><img class="img-fluid img-circle" src="data:image/jpg;base64,${profileUser.getProfileImage()}" alt="Image"></a>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
	<section class="details">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">

					<div class="details-box row">
						<div class="col-lg-9">
							<div class="content-box">
								<h4>${profileUser.getName() } <i class="fa fa-check"></i></h4>
								<p>${profileUser.getEmail() }  <span
										class="hashtag">#${profileUser.getName() }</span></p>
							</div>
						</div>
						<div class="col-lg-3">
							<c:choose>
								<c:when test="${userId eq profileId}">
								</c:when>
								<c:when test="${connection==true}">
									<div class="follow-box">
										<a href="#" class="kafe-btn kafe-btn-mint"><i class="fa fa-check"></i> Following</a>
									</div>
								</c:when>
								<c:when test="${connection==false}">
									<div class="follow-box">
										<a href="followFromProfile?userId=${userId}&profileId=${profileUser.getId()}&requestPersonId=${profileUser.getId()}" class="kafe-btn kafe-btn-mint"><i class="fa fa-plus"></i> Follow</a>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>
	<section class="home-menu">
		<div class="container">
			<div class="row">

				<div class="menu-category">
					<ul class="menu">
						<li class="current-menu-item"><a href="photo_profile.html">Posts <span>${profileUser.getPosts().size()}</span></a></li>
						<li><a href="./instagram_followers_list.html">Followers <span>${profileUser.getFriendsId().size()}</span></a></li>
						<!-- <li><a href="./instagram_followers_list.html">Following <span>1200</span></a></li>  -->
					</ul>
				</div>

			</div>
		</div>
	</section>
	<section class="newsfeed">
		<div class="container">

			<div class="row">
				<c:forEach items="${profileUser.getPosts()}" var="userPost">
					
					<div class="col-lg-4">
						<a href="seePost?userId=${userId}&postId=${userPost.getId()}&profileId=${profileId}">
							<div class="explorebox" style="background: linear-gradient( rgba(34,34,34,0.2), rgba(34,34,34,0.2)), url('data:image/jpg;base64,${userPost.getPost()}') no-repeat;
			          background-size: cover;
	                  background-position: center center;
	                  -webkit-background-size: cover;
	                  -moz-background-size: cover;
	                  -o-background-size: cover;">
								<div class="explore-top">
									<div class="explore-like"><i class="fa fa-heart"></i> <span>${userPost.getLikes().size()}</span></div>
								</div>
							</div>
						</a>
					</div>
					
				</c:forEach>
				<div class="col-lg-4">
					<a href="#myModal" data-toggle="modal">
						<div class="explorebox" style="background: linear-gradient( rgba(34,34,34,0.2), rgba(34,34,34,0.2)), url('resources/assets/img/posts/14.jpg') no-repeat;
		          background-size: cover;
                  background-position: center center;
                  -webkit-background-size: cover;
                  -moz-background-size: cover;
                  -o-background-size: cover;">
							<div class="explore-top">
								<div class="explore-like"><i class="fa fa-heart"></i> <span>14,100</span></div>
								<div class="explore-circle pull-right"><i class="far fa-bookmark"></i></div>
							</div>
						</div>
					</a>
				</div>

			</div>
		</div>
	</section>
	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">

					<div class="row">

						<div class="col-md-8 modal-image">
							<img class="img-responsive" src="resources/assets/img/posts/15.jpg" alt="Image" />
						</div>
						<div class="col-md-4 modal-meta">
							<div class="modal-meta-top">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									<span aria-hidden="true">×</span><span class="sr-only">Close</span>
								</button>
								<div class="img-poster clearfix">
									<a href="#"><img class="img-responsive img-circle" src="resources/assets/img/users/18.jpg"
											alt="Image" /></a>
									<strong><a href="#">Benjamin</a></strong>
									<span>12 minutes ago</span><br />
									<a href="#" class="kafe kafe-btn-mint-small"><i class="fa fa-check-square"></i>
										Following</a>
								</div>

								<ul class="img-comment-list">
									<li>
										<div class="comment-img">
											<img src="resources/assets/img/users/17.jpg" class="img-responsive img-circle"
												alt="Image" />
										</div>
										<div class="comment-text">
											<strong><a href="#">Anthony McCartney</a></strong>
											<p>Hello this is a test comment.</p> <span class="date sub-text">on December
												5th, 2016</span>
										</div>
									</li>
									<li>
										<div class="comment-img">
											<img src="resources/assets/img/users/15.jpg" class="img-responsive img-circle"
												alt="Image" />
										</div>
										<div class="comment-text">
											<strong><a href="#">Vanessa Wells</a></strong>
											<p>Hello this is a test comment and this comment is particularly very long
												and it goes on and on and on.</p> <span>on December 5th, 2016</span>
										</div>
									</li>
									<li>
										<div class="comment-img">
											<img src="resources/assets/img/users/14.jpg" class="img-responsive img-circle"
												alt="Image" />
										</div>
										<div class="comment-text">
											<strong><a href="#">Sean Coleman</a></strong>
											<p class="">Hello this is a test comment.</p> <span class="date sub-text">on
												December 5th, 2016</span>
										</div>
									</li>
									<li>
										<div class="comment-img">
											<img src="resources/assets/img/users/13.jpg" class="img-responsive img-circle"
												alt="Image" />
										</div>
										<div class="comment-text">
											<strong><a href="#">Anna Morgan</a></strong>
											<p class="">Hello this is a test comment.</p> <span class="date sub-text">on
												December 5th, 2016</span>
										</div>
									</li>
									<li>
										<div class="comment-img">
											<img src="resources/assets/img/users/3.jpg" class="img-responsive img-circle"
												alt="Image" />
										</div>
										<div class="comment-text">
											<strong><a href="#">Allison Fowler</a></strong>
											<p class="">Hello this is a test comment.</p> <span class="date sub-text">on
												December 5th, 2016</span>
										</div>
									</li>
								</ul>
								<div class="modal-meta-bottom">
									<ul>
										<li><a class="modal-like" href="#"><i class="fa fa-heart"></i></a><span
												class="modal-one"> 786,286</span> |
											<a class="modal-comment" href="#"><i class="fa fa-comments"></i></a><span>
												786,286</span> </li>
										<li>
											<span class="thumb-xs">
												<img class="img-responsive img-circle" src="resources/assets/img/users/13.jpg"
													alt="...">
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
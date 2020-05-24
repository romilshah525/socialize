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
	  <script src="https://onesignal.github.io/emoji-picker/lib/js/util.js"></script>
<script
src="https://onesignal.github.io/emoji-picker/lib/js/jquery.emojiarea.js"></script>
<script
src="https://onesignal.github.io/emoji-picker/lib/js/emoji-picker.js"></script>
<script
src="https://cdnjs.cloudflare.com/ajax/libs/react/15.3.1/react.js"></script>
<script
src="https://cdnjs.cloudflare.com/ajax/libs/react/15.3.1/react-dom.js"></script>

<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link rel="stylesheet"
href="https://onesignal.github.io/emoji-picker/lib/css/emoji.css">
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
													
														<c:choose>
															<c:when test="${homeMessage.getMediaType() eq 'PICTURE' }">
																<!-- <i class="material-icons"></i> --> you have received Picture..
															</c:when>
															<c:when test="${homeMessage.getMediaType() eq 'VIDEO' }">
																<!-- <i class="fa fa-video-camera"> --> you have received Video..
															</c:when>
															<c:when test="${homeMessage.getMediaType() eq 'DOCUMENT' }">
																<!-- <i class="fa fa-file-text"> </i> --> you have received Document..
															</c:when>
															<c:when test="${homeMessage.getMediaType() eq null }">
																${homeMessage.getMessage()}
															</c:when>
														</c:choose>
													
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
								<a class="dropdown-item" href="stories?userId=${userId}&statusId=0&prev=0&next=1"><span>New Story</span></a>
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
			<div class="p-2 nav-icon-lg  mint-green">
				<a class="nav-icon" href="stories?userId=${userId}&statusId=0&prev=0&next=1"><em class="fa fa-align-left"></em>
					<span>Stories</span>
				</a>
			</div>
			<div class="p-2 nav-icon-lg clean-black">
				<a class="nav-icon" href="profile?userId=${userId}&profileId=${userId}"><em class="fa fa-user"></em>
					<span>Profile</span>
				</a>
			</div>
		</div>
	</section>


	<section class="chat">
		<div class="container">
			<div class="row">

				<div class="messages-box">
					<div class="row">
						<div class="col-lg-4 col-md-12 no-pdd">
							<div class="messages-container">

								<div class="message-header">
									<div class="message-title">
										<h4>Messages</h4>
									</div>
									<div class="search-area">
										<div class="input-field">
											<input placeholder="Search" type="text">
											<i class="fa fa-search"></i>
										</div>
									</div>
								</div>

								<div class="messages-list">
									<ul>
										<c:forEach items="${chatScreenContactWithMessage }" var="friendUser">
											<a href="viewChat?userId=${userId}&friendId=${friendUser.getId()}">
												<li>
													<div class="user-message-details">
														<div class="user-message-img">
															<img src="data:image/jpg;base64,${friendUser.getProfileImage()}" class="img-responsive img-circle" style="border-radius: 50%"
																alt="">
															
														</div>
														<div class="user-message-info">
															<h4>${friendUser.getName() }</h4>
															
															<c:if test="${friendUser.getMessages().size()==0}">
																<p>no message</p>
															</c:if>
															<c:if test="${friendUser.getMessages().size() != 0}">
															
																<c:forEach items="${friendUser.getMessages()}" begin="${friendUser.getMessages().size()-1}" var="message">
																
																	<c:choose>
																		<c:when test="${message.getMediaType() eq 'PICTURE' }">
																			<!-- <i class="material-icons"></i> --> <p>you have received Picture..</p>
																		</c:when>
																		<c:when test="${message.getMediaType() eq 'VIDEO' }">
																			<!-- <i class="fa fa-video-camera"> --> <p>you have received Video..</p>
																		</c:when>
																		<c:when test="${message.getMediaType() eq 'DOCUMENT' }">
																			<!-- <i class="fa fa-file-text"> </i> --> <p>you have received Document..</p>
																		</c:when>
																		<c:when test="${message.getMediaType() eq null }">
																			<p>${message.getMessage()}</p>
																		</c:when>
																	</c:choose>
																
																	<span class="time-posted">${message.getDate()}</span>
																	
																</c:forEach> 
																
															</c:if>
															
															
														</div>
														
															<c:if test="${message.getReceiverId() eq userId}">
																<span class="message-notification"> 
																new
																</span>
															</c:if>
														
													</div>
												</li>
											</a>
										</c:forEach>
										
										
									</ul>
								</div>

							</div>
						</div>

						<div class="col-lg-8 col-md-12 pd-right-none pd-left-none">
							<div class="conversation-box">

								<div class="conversation-header">
									<div class="user-message-details">
										<div class="user-message-img">
											<img src="data:image/jpg;base64,${friend.getProfileImage()}" class="img-responsive img-circle" alt="">
										</div>
										<div class="user-message-info">
											<h4>${friend.getName()}</h4>
											<p>Online</p>
										</div>
									</div>
									<a href="#" title=""><i class="fa fa-ellipsis-v"></i></a>
								</div>
								
								<div class="conversation-container">
									<c:forEach items="${friend.getMessages()}" var="friendMessage">
										
										<c:choose>
											<c:when test="${friendMessage.getSenderId() eq userId}">
												<div class="convo-box pull-right">
													<div class="convo-area pull-right">
														<div class="convo-message">
															
															<c:choose>
																<c:when test="${friendMessage.getMediaType() eq 'PICTURE' }">
																	<!-- <i class="material-icons"></i> --> <p>you have received Picture..</p>
																</c:when>
																<c:when test="${friendMessage.getMediaType() eq 'VIDEO' }">
																	<!-- <i class="fa fa-video-camera"> --> <p>you have received Video..</p>
																</c:when>
																<c:when test="${friendMessage.getMediaType() eq 'DOCUMENT' }">
																	<!-- <i class="fa fa-file-text"> </i> --> <p>you have received Document..</p>
																</c:when>
																<c:when test="${friendMessage.getMediaType() eq null }">
																	<p>${friendMessage.getMessage()}</p>
																</c:when>
															</c:choose>
														
														</div>
														<span>Sat, Aug 23, 1:08 PM</span>
													</div>
													<!-- <div class="convo-img">
														<img src="resources/assets/img/users/2.jpg" alt="" class="img-responsive img-circle">
													</div> -->
												</div>
											</c:when>
											
											<c:when test="${friendMessage.getReceiverId() eq userId}">
												<div class="convo-box convo-left">
													<div class="convo-area convo-left">
														<div class="convo-message">
															<c:choose>
																
																<c:when test="${friendMessage.getMediaType() eq 'PICTURE' }">
																	<!-- <i class="material-icons"></i> --> <p>you have received Picture..</p>
																</c:when>
																<c:when test="${friendMessage.getMediaType() eq 'VIDEO' }">
																	<!-- <i class="fa fa-video-camera"> --> <p>you have received Video..</p>
																</c:when>
																<c:when test="${friendMessage.getMediaType() eq 'DOCUMENT' }">
																	<!-- <i class="fa fa-file-text"> </i> --> <p>you have received Document..</p>
																</c:when>
																<c:when test="${friendMessage.getMediaType() eq null }">
																	<p>${friendMessage.getMessage()}</p>
																</c:when>
															</c:choose>
														</div>
														<span>5 minutes ago</span>
													</div>
													<!-- <div class="convo-img">
														<img src="resources/assets/img/users/6.jpg" alt="" class="img-responsive img-circle">
													</div> -->
												</div>
											</c:when>
										</c:choose>
										
									</c:forEach>
								</div>
								
									
								<div class="type_messages">
									<div class="input-field">
									<form action="sendMessage?userId=${userId}&friendId=${friend.getId()}" method="post">
										
										<input data-emoji-input="unicode" type="text"
                                        data-emojiable="true" name="message" class="form-control"
                                        id="message"
                                        placeholder="Type your message"
                                        aria-describedby="validationTooltipUsernamePrepend">
									</form>
										
										<ul class="imoji">
											<li><a href="#"><i class="fa fa-smile"></i></a></li>
											<li><a href="#"><i class="fa fa-image"></i></a></li>
											<li><a href="#"><i class="fa fa-paperclip"></i></a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="resources/assets/js/jquery.min.js"></script>
	<script src="resources/assets/js/bootstrap.min.js"></script>
	<script src="resources/assets/js/base.js"></script>
	<script src="resources/assets/plugins/slimscroll/jquery.slimscroll.js"></script>
	 <script>
 
 $(function() {
     // Initializes and creates emoji set from sprite sheet
     window.emojiPicker = new EmojiPicker({
       emojiable_selector: '[data-emojiable=true]',
       assetsPath: 'http://onesignal.github.io/emoji-picker/lib/img/',
       popupButtonClasses: 'fa fa-smile-o'
     });
     // Finds all elements with `emojiable_selector` and converts them to rich emoji input fields
     // You may want to delay this step if you have dynamically created input fields that appear later in the loading process
     // It can be called as many times as necessary; previously converted input fields will not be converted again
     window.emojiPicker.discover();
   });
 
    </script>
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
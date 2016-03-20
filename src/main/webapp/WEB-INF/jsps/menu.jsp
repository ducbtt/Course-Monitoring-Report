
<nav class="navbar navbar-default" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home"><i class="fa fa-home"></i></a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<c:if test="${not empty sessionUser}">
					<c:if test="${sessionUser.hasAdminRole() }">
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle">Users <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="allUsers">All Users</a></li>
								<li><a href="createUser">Create new user</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle">Roles <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="allRoles">All Roles</a></li>
								<li><a href="createRole">Create new role</a></li>
							</ul></li>
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle">Courses <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="allCourses">All Courses</a></li>
								<li><a href="createCourse">Create new course</a></li>
							</ul></li>
							<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle">Faculties <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="allFaculties">All Faculties</a></li>
								<li><a href="createFaculty">Create new faculty</a></li>
							</ul></li>
					</c:if>
					<c:if test="${sessionUser.hasCLRole() or sessionUser.hasCMRole()}">
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle">Course Management <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="allMyCourses?usrId=${sessionUser.usrId}">My Courses</a></li>
								<li><a href="allCmrs?usrId=${sessionUser.usrId}">My CMRs</a></li>
							</ul></li>
					</c:if>
					<c:if
						test="${sessionUser.hasPVCRole() or sessionUser.hasDLTRole()}">
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle">CMR Management<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="allCmrs?usrId=${sessionUser.usrId}">All CMRs</a></li>
							</ul></li>
					</c:if>
					<c:if test="${sessionUser.hasGuestRole()}">
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							class="dropdown-toggle">Reports<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Statistical Reports</a></li>
								<li><a href="#">Exceptional Reports</a></li>
							</ul></li>
					</c:if>
				</c:if>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
</nav>


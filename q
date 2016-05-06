[33mcommit c4ddac457fabdd4da7f540ebb1da7337a5f1a334[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Fri May 6 14:09:52 2016 +0300

    Added a few fixes for CRUD actions

[33mcommit 882c201bd1fe73d0254fbc272f768fa0f2a0f494[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Fri May 6 09:02:51 2016 +0300

    Added a few fixes. Need to fix RaceStation CRUD & Ticket CRUD.

[33mcommit e5ca4a679e7e5effe887a13bd34eee5104e9c9a1[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Thu May 5 12:31:47 2016 +0300

    Fixed a few issues in Train CRUD. Added validation imporvments.

[33mcommit 0495df33eca7fd44933d2f506e817f0b61107108[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Thu May 5 11:36:23 2016 +0300

    Fixed Ticket CRUD. Added improvments in validation.

[33mcommit 1929b3f391ab0a9fa906ae4b36e8a82f98f19e76[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Thu May 5 09:33:59 2016 +0300

    Fixed issue in TrainType CRUD, added validation.

[33mcommit 9f9947ffc78c59e9b74d4e18d18a46c1033e4506[m
Merge: 136edc5 cc698ee
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Wed May 4 13:31:04 2016 +0300

    Merge branch 'master' of https://github.com/SuperDruper/SPP_Railway
    
    # Conflicts:
    #	src/main/resources/struts.xml
    #	src/main/webapp/index.jsp
    #	src/main/webapp/modules/app.js

[33mcommit 136edc5f5930d48ff6d7a652ef366bd38b0070b0[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Wed May 4 13:18:41 2016 +0300

    Added CRUD for tickets. Fixed some issus with user crud.

[33mcommit cc698ee1f230c52eb95b09fc104414036538302a[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue May 3 22:41:37 2016 +0300

    1. Added files to commit, that were forgotten in previous commit.

[33mcommit 4ee89022bec3188d8348017edb1ec14fc2cc3f60[m
Merge: b0aaa27 7d348f5
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue May 3 22:40:52 2016 +0300

    Merge remote-tracking branch 'origin/master'

[33mcommit b0aaa27dcbadd5a8ca0d176359a1bc491a17511b[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue May 3 22:39:46 2016 +0300

    1. Added files to commit, that were forgotten in previous commit.

[33mcommit 4293edba0704b0cf4317cca39cf75416ed1f9116[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue May 3 22:38:31 2016 +0300

    1. RaceDetails Action, page were written
    2. Some daos and services were added and improved.
    3. Method that returns String error list named validate() was added in ValidationUtils.
    4. Models for communication with client's JS created.
    5. Some angular functions were moved to file service.js.
    6. Some unused classes were removed.

[33mcommit 7d348f5e6a8bd2409f9a8f00ab4b9a1940303577[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Tue May 3 11:22:57 2016 +0300

    Added fixes into User CRUD.

[33mcommit b211097690fb1310114d414928e34c1561b92343[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Tue May 3 11:22:29 2016 +0300

    Added fixes into User CRUD.

[33mcommit 17a4ba524de29ae55def677417d3e3beca8df8b1[m
Merge: d31d653 2581523
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Sun May 1 18:53:00 2016 +0300

    Merge branch 'master' of https://github.com/SuperDruper/SPP_Railway

[33mcommit d31d653e259e88b0539b9460bb5bff26d5cee2c1[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Sun May 1 18:52:52 2016 +0300

    strugling with bootsrap calendar

[33mcommit a5db3012cd95e0e75f3925b596b907ebf8ec1f1f[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Sun May 1 18:51:48 2016 +0300

    Try to implement race_station CRUD with bootstrap calendar(

[33mcommit 25815231fc9fb3e4ecd32d5318277d467ddef7f5[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Sun May 1 10:45:37 2016 +0300

    Small bug fix after merge.

[33mcommit 5d5cf10c2fb1f8e928d886d6abe998fdc7ed8184[m
Merge: 5fc9b14 cb51378
Author: SuperDruper <profprof1997@gmail.com>
Date:   Sun May 1 02:02:53 2016 +0300

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #	src/main/java/code/controller/user/UserListShowingAction.java
    #	src/main/resources/struts.xml
    #	src/main/webapp/index.jsp
    #	src/main/webapp/modules/app.js
    #	src/main/webapp/modules/user/list/list.view.html
    #	src/main/webapp/modules/user/register/register.view.html

[33mcommit 5fc9b14ec19746afa195a5afc4d77dd60d212c06[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Sun May 1 01:34:26 2016 +0300

    1. Interceptor Authorize and Authorize annotation were written and used.
    2. Profile getting and editing were added;
    3. Validation framework was included in project;
    4. Validation annotations were added to User class;
    5. Structure of struts.xml were changed and json with Authorize interceptor was added to default package interceptor;
    6. Login was fixed;
    7. Logout was added.

[33mcommit cb513784e097d750a5775ac58a7dd2a649288501[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Tue Apr 26 12:06:11 2016 +0300

    Added all cruds from diagram. Need to fix issue only in RaceStation CRUD

[33mcommit 078924f1e11ee2ab1818f402a6522b834b1e051e[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Sun Apr 24 23:23:48 2016 +0300

    Added select drop-down list to make(or change) answer

[33mcommit 7691d91899228aa48d50f1b026920d40aeb17351[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Mon Apr 11 12:51:24 2016 +0300

    Added fixes in implemented CRUD. Added support bootstrap styles

[33mcommit f2a5410be388c98821d39ce76409ddbb2850c3f5[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Mon Apr 11 09:24:51 2016 +0300

    Added new services & controllers. Currenlty CRUD for Train, TrainType, Role

[33mcommit 0c3560c9dbb9f25e551dc5f3f8be38f0f346f54a[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Sat Apr 9 23:32:05 2016 +0300

    1. Structure of struts.xml was edited
    2. Login was added
    3. Bugs with url routing was fixed

[33mcommit a59fc1ca977a7d79589aa70bf840ac6d902bc7bb[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Wed Apr 6 23:49:20 2016 +0300

    1. GetAction class added. All the actions that called from GET request should extend it.
    2. PostAction class added. All the actions that called from POST request should extend it.
    3. getUserByLogin method in IUserDao was removed.
    4. getModelByUniqueStringField and getModelListByStringField methods were added in GenericService.
    5. RoleService was fixed from bugs.
    6. User registration was added.
    7. Temp files for my problems "listusers.jsp" and "register.jsp" were removed.
    8. CSS were added and included in index.jsp

[33mcommit 25f67a7c22bcf2d533e6fc32b398ac81e14a6989[m
Merge: 527eae4 991262a
Author: SuperDruper <profprof1997@gmail.com>
Date:   Mon Apr 4 00:43:06 2016 +0300

    Merge remote-tracking branch 'origin/master'
    
    # Conflicts:
    #	src/main/java/tests/hibernatedao/DatabaseGeneratorHelper.java

[33mcommit 527eae4b3fb2292740ecfcfa21decd61424bff5f[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Sun Apr 3 21:20:21 2016 +0300

    1. Angular firstly used

[33mcommit 991262acf8399de96663befcbc342d22aa79df65[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Sun Apr 3 20:39:34 2016 +0300

    Added for DatabaseGeneratorHelper dbRaceManagers for generate database objects: DBRoleManager & DBRaceManager

[33mcommit 65768a12cd95c7617654575f59cecf7fafd76b1a[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Sun Apr 3 14:40:12 2016 +0300

    Fixed issue with testing basic CRUD operation with HibernateDAO. Replaced some methods in GenericHibernateDao: get entity by PK for class with NON proxy analog(via proxy cause exception).

[33mcommit 093cb3fe104ec0fe62800523581ba203f7c44ddc[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Sun Apr 3 09:02:18 2016 +0300

    Added tests for : DatabaseGeneratorHelper, GenericHibernateDao, HibernateDaoFactory, HibernateUtils, and for GenericService also.

[33mcommit 8e63bd6c4a5901e84d915eedad85a168c0b6b91b[m
Author: dzmitry.antonenka <schurik77799@gmail.com>
Date:   Sun Mar 20 16:22:32 2016 +0300

    "Created hibernate daoa and services for entities: {Train, Race, RaceStation, Ticket, Route, Station}.

[33mcommit bbbc092ff3bcc808efb6a56fdda36ed20dc27f3f[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Sat Mar 19 22:40:36 2016 +0300

    1. AbstractFactory and HibernateFactory add;\n2. Unusefull examples were removed; Generic Service and GenericHibernateDao was added;\n3. UserDao, UserService, RoleService were added; 4.Register, Login, Profile and UserList Actions were added;\n5. JSPs for actions in 4-th pound were added.

[33mcommit f05798637e63b2d10677fbbec0f2f78f45eb7dc7[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue Mar 15 23:49:40 2016 +0300

    1. Remove target and .idea folders

[33mcommit 27a1e0a64ff9965a71c0f7e5e5056ad2c44d6a91[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue Mar 15 19:53:57 2016 +0300

    1. Script for db generating added.
    2. FileForContributors is for recording:  everything that is necessary to do and warnings, that may have place while using git, in general everything, that can't be done to all contributors with the help of git (setup database, for example).

[33mcommit d97933f5c3c906d8a70ad8a26ee8798472018b7c[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue Mar 15 19:21:00 2016 +0300

    First working project with working struts2 and hibernate!!!

[33mcommit 1fbaac7c6b020724a15bdbc2669695937a3c08b7[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue Mar 1 15:50:49 2016 +0300

    1. Added dao interface and MySqlDao
    2. Added unit test MySQLConnectionTest
    3. Added IService interface with basic CRUD methods for implementation
    4. Added model distance and it's service - Distance Service
    5. Added ServiceException to

[33mcommit 9f1c398b9b0a82d99a2bf7f093d47ee42bdb6218[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Mon Feb 29 10:07:43 2016 +0300

    1. Database structure was fixed;
    2. SQL script war changed and now it fills table with default values;
    3. Previous commit is fake!!!

[33mcommit b5efeaced17ec565696b99c3b3633879afd43501[m
Merge: 6864cac 5a81759
Author: SuperDruper <profprof1997@gmail.com>
Date:   Mon Feb 29 09:45:18 2016 +0300

    Merge remote-tracking branch 'origin/master'

[33mcommit 6864cac11bbacd59a0c17788f127c406c09ab483[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Mon Feb 29 09:44:26 2016 +0300

    1. Database structure was fixed;
    2. SQL script war changed and now it fills table with default values.

[33mcommit 5a81759b6b02434171b64fd4df1ff5693facd6b6[m
Author: AccuType-911 <AccuType-911@users.noreply.github.com>
Date:   Fri Feb 26 18:24:08 2016 +0300

    Create README.md

[33mcommit 9f58893057867285b1c902ce858757297386de78[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Fri Feb 26 18:20:13 2016 +0300

    The folder 'out' was remoed

[33mcommit dd2cff300c53594f1b4126e0ce5dbcdc92d6cad7[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Fri Feb 26 18:01:21 2016 +0300

    Trying to commit with the help of IDE
    If exerything is fine it will delete .idea folder

[33mcommit 470be7c00b0e264d376c4d23168f81bc49dd9765[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue Feb 23 22:24:30 2016 +0300

    Hello world was done
    
    1. Struts 2 installed

[33mcommit b11259217fd80ddc23b4a3ddd45497444cfb28fc[m
Author: SuperDruper <profprof1997@gmail.com>
Date:   Tue Feb 23 15:45:27 2016 +0300

    First commit
    
    Project with integrated maven

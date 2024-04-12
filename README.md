This is my realisation of green atom project for internship enrollment.
I have added open api for more comfortable using experience, when project is running you can use following link: http://localhost:8080/swagger-ui/index.html
You can use those users for /api/v1/auth/login endpoint:
1. This user don't have access to put/delete endpoints
"username": "johndoesimple@gmail.com",
"password": "JohnDoeSimple"
2. This user have permissions for any endpoint
"username": "johndoeadmin@gmail.com",
"password": "JohnDoeAdmin""

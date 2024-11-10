from flask import *
import pymysql
from functions import *

app = Flask(__name__)
app.secret_key = "dwmcfkcfnewmkfenmwfiw2325434324"

@app.route('/')
def home():
    return '<p>Click <a href="/sign-up">this link to redirect you</a></p>'

@app.route('/sign-up', methods=['GET', 'POST'])
def sign_up():
    if request.method == 'GET':
        return render_template("signup.html")
    else: # POST
        username = request.form['username']
        email = request.form['email']
        password = request.form['password']
        title = request.form['title']
        hashed_password = hash_salt_password(password)
        connection = pymysql.connect(host='localhost', user='root', password='', database='augustine_cyber')
        cursor = connection.cursor()
        sql = 'insert into users (username, email, password, title) values(%s, %s, %s, %s)'
        try:
            cursor.execute(sql, (username, email, hashed_password, title))
            connection.commit()
            # return 'Saved successfully'
            return render_template('signup.html', message = "Details saved successfully")
        except:
            connection.rollback()
            # return "Not saved"
            return render_template("signup.html", error = "Details not saved")
        
@app.route('/login', methods = ['GET', 'POST'])
def login():
    if request.method == 'GET':
        return render_template("login.html")
    else: #POST
        username = request.form['username']
        password = request.form['password']
        input_hashed_password = hash_salt_password(password)
        connection = pymysql.connect(host='localhost', user='root', password='', database='augustine_cyber')
        cursor = connection.cursor()
        sql = 'select * from users where username = %s and password = %s'
        cursor.execute(sql, (username, input_hashed_password))
        user = cursor.fetchone()
        if (cursor.rowcount == 0):
            return render_template("login.html", error='Invalid credentials')
        else:
            try:
                if (input_hashed_password != user[2]):
                    return render_template("login/html", error="Invalid credentials")
                elif (input_hashed_password == user[2]):
                    return render_template("login.html", message="Correct login details. Please wait while we redirect you")
            except:
                if connection:
                    connection.rollback()
                return "An error occured. Please be patient while we resolve the problem"
            finally:
                if connection:
                    connection.close()
    


if __name__ == '__main__':
    app.run(debug=True)
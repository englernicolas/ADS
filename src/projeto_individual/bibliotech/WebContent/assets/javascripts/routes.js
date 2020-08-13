import Home from "/bibliotech/assets/javascripts/components/Home.js"
import MyAccount from "/bibliotech/assets/javascripts/components/EditProfile.js"
/*
import Students from "/bibliotech/assets/javascripts/components/Students.js"
import Librarians from "/bibliotech/assets/javascripts/components/Librarians.js"
import Books from "/bibliotech/assets/javascripts/components/Books.js"
import Loans from "/bibliotech/assets/javascripts/components/Loans.js"
*/

let routes;
export default routes =  [
    {
        path: '/home',
        component: Home
    },
    {
        path: '/profile',
        component: MyAccount
    },
    /*
    {
        path: '/students',
        component: Students
    },
    {
        path: '/librarians',
        component: Librarians
    },
    {
        path: '/books',
        component: Books
    },
    {
        path: '/loans',
        component: Loans
    },
    */
]
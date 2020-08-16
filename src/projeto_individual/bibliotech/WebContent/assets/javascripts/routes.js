import Home from "./components/Home.js"
import MyAccount from "./components/EditProfile.js"
import Students from "./components/Students.js"
import Librarians from "./components/Librarians.js"
/*
import Books from "/bibliotech/assets/javascripts/components/Books.js"
import Loans from "/bibliotech/assets/javascripts/components/Loans.js"
import Loans from "/bibliotech/assets/javascripts/components/Reports.js"
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
    {
        path: '/students',
        component: Students
    },
    {
        path: '/librarians',
        component: Librarians
    },
    /*
    {
        path: '/books',
        component: Books
    },
    {
        path: '/loans',
        component: Loans
    },
    {
        path: '/reports',
        component: Reports
    },
    */
]
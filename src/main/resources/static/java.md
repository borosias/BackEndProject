classDiagram
direction BT
class AuthController {
  + AuthController() 
  - UserService userService
  + signIn(Credentials) JwtToken
  + signUp(Credentials) void
   UserService userService
}
class BackEndProjectApplication {
  + BackEndProjectApplication() 
  + main(String[]) void
}
class Credentials {
  + Credentials(String, String) 
  + Credentials() 
  - String username
  - String password
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
  + toString() String
   String password
   String username
}
class DefaultRoles {
  - DefaultRoles() 
}
class JwtFilter {
  + JwtFilter() 
  - JwtProvider jwtProvider
  - UserDetailsService userDetailsService
  # doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain) void
  - getTokenFromRequest(HttpServletRequest) Optional~String~
   UserDetailsService userDetailsService
   JwtProvider jwtProvider
}
class JwtProvider {
  + JwtProvider() 
  + getUsernameFromToken(String) String
  + generateToken(String) String
  + toDecodedJWT(String) Optional~DecodedJWT~
}
class JwtToken {
  + JwtToken(String, String, String, String) 
  + JwtToken() 
  - String expiresAt
  - String token
  - String algorithm
  - String type
  + hashCode() int
  + toString() String
  + equals(Object) boolean
  # canEqual(Object) boolean
   String type
   String token
   String algorithm
   String expiresAt
}
class JwtTokenMapper {
<<Interface>>
  + toPayload(DecodedJWT) JwtToken
}
class ModelMapperConfig {
  + ModelMapperConfig() 
  + modelMapper() ModelMapper
}
class Post {
  + Post() 
  - String text
  - LocalDateTime createdAt
  - Topic topic
  - User creator
  - Long id
  + toString() String
  + equals(Object) boolean
  + hashCode() int
   String text
   LocalDateTime createdAt
   Long id
   User creator
   Topic topic
}
class PostAddPayload {
  + PostAddPayload(String, String, Long) 
  + PostAddPayload() 
  - String creatorUsername
  - Long topicId
  - String text
  + toString() String
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
   String text
   Long topicId
   String creatorUsername
}
class PostController {
  + PostController(PostService, ModelMapper) 
  + update(PostUpdatePayload, long) ResponseEntity~PostPayload~
  + addOne(PostAddPayload) ResponseEntity~PostPayload~
  + delete(long) ResponseEntity~PostPayload~
  + getAll(int, int, String) ResponseEntity~List~PostPayload~~
  + getById(long) ResponseEntity~PostPayload~
}
class PostPayload {
  + PostPayload() 
  + PostPayload(Long, String, String, Long, LocalDateTime) 
  - Long id
  - LocalDateTime createdAt
  - Long topicId
  - String creatorUsername
  - String text
  + hashCode() int
  + toString() String
  + equals(Object) boolean
  # canEqual(Object) boolean
   String text
   LocalDateTime createdAt
   String creatorUsername
   Long id
   Long topicId
}
class PostRepository {
<<Interface>>
  + deleteByCreator(User) void
  + findAllByCreator(User) List~Post~
}
class PostService {
  + PostService(PostRepository, UserRepository, TopicRepository) 
  + create(PostAddPayload) Post
  + update(PostUpdatePayload, long) boolean
  + findAll(int, int, String) Page~Post~
  + findById(Long) Optional~Post~
  + deleteById(Long) void
}
class PostUpdatePayload {
  + PostUpdatePayload(String) 
  + PostUpdatePayload() 
  - String text
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
  + toString() String
   String text
}
class Role {
  + Role() 
  - Long id
  - Set~Role~ children
  - String name
  - Role parent
  - int rank
  - Set~User~ users
  + toString() String
  + hashCode() int
  + asAuthorities() Set~SimpleGrantedAuthority~
  + equals(Object) boolean
  + addChild(Role) void
   String name
   Role parent
   Set~Role~ children
   Long id
   int rank
   Set~User~ users
   Set~Role~ allChildren
}
class RoleAddPayload {
  + RoleAddPayload(String, Integer) 
  + RoleAddPayload() 
  - String name
  - Integer rank
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
  + toString() String
   String name
   Integer rank
}
class RoleController {
  + RoleController(RoleService, ModelMapper) 
  + getById(String) ResponseEntity~RolePayload~
  + getAll(int, int, String) ResponseEntity~List~RolePayload~~
  + addOne(RoleAddPayload) ResponseEntity~RolePayload~
  + update(RoleUpdatePayload, String) ResponseEntity~RolePayload~
  + deleteByName(String) ResponseEntity~RolePayload~
}
class RoleLoader {
  + RoleLoader(RoleRepository) 
  ~ RoleRepository roleRepository
  + run(String[]) void
   RoleRepository roleRepository
}
class RoleNotFoundException {
  + RoleNotFoundException(String) 
  + RoleNotFoundException(String, Throwable) 
}
class RolePayload {
  + RolePayload(String, Integer) 
  + RolePayload() 
  - String name
  - Integer rank
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
  + toString() String
   String name
   Integer rank
}
class RoleRepository {
<<Interface>>
  + deleteByName(String) void
  + existsByName(String) boolean
  + save(Role) Role
  + findByName(String) Optional~Role~
  + findByRankGreaterThan(int) Collection~Role~
}
class RoleService {
  + RoleService(RoleRepository) 
  + update(RoleUpdatePayload, String) boolean
  + findByName(String) Optional~Role~
  + deleteByName(String) void
  + findAll(int, int, String) Page~Role~
  + create(RoleAddPayload) Role
}
class RoleUpdatePayload {
  + RoleUpdatePayload(String, Integer) 
  + RoleUpdatePayload() 
  - String name
  - Integer rank
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
  + toString() String
   String name
   Integer rank
}
class Topic {
  + Topic() 
  - String title
  - Set~Post~ posts
  - Long id
  - String description
  - LocalDateTime createdAt
  - User creator
  + equals(Object) boolean
  + toString() String
  + addPost(Post) void
  + hashCode() int
   String description
   LocalDateTime createdAt
   String title
   Long id
   Set~Post~ posts
   User creator
}
class TopicAddPayload {
  + TopicAddPayload() 
  + TopicAddPayload(String, String, String) 
  - String creatorUsername
  - String title
  - String description
  + toString() String
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
   String description
   String creatorUsername
   String title
}
class TopicController {
  + TopicController(TopicService, ModelMapper) 
  + getByUserId(long) ResponseEntity~List~PostPayload~~
  + getAll(int, int, String) ResponseEntity~List~TopicPayload~~
  + addOne(TopicAddPayload) ResponseEntity~TopicPayload~
  + getById(long) ResponseEntity~TopicPayload~
  + update(TopicUpdatePayload, long) ResponseEntity~TopicPayload~
  + delete(long) ResponseEntity~TopicPayload~
}
class TopicPayload {
  + TopicPayload(Long, String, String, String, LocalDateTime) 
  + TopicPayload() 
  - String creatorUsername
  - Long id
  - String description
  - LocalDateTime createdAt
  - String title
  + hashCode() int
  + equals(Object) boolean
  + toString() String
  # canEqual(Object) boolean
   String description
   LocalDateTime createdAt
   String creatorUsername
   String title
   Long id
}
class TopicRepository {
<<Interface>>

}
class TopicService {
  + TopicService(TopicRepository, UserRepository) 
  + deleteById(Long) void
  + findAll(int, int, String) Page~Topic~
  + findById(Long) Optional~Topic~
  + update(TopicUpdatePayload, long) boolean
  + create(TopicAddPayload) Topic
}
class TopicUpdatePayload {
  + TopicUpdatePayload(String, String) 
  + TopicUpdatePayload() 
  - String title
  - String description
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
  + toString() String
   String description
   String title
}
class User {
  + User() 
  - LocalDateTime createdAt
  - Set~Topic~ topics
  - String password
  - Role role
  - String username
  - Long id
  - Set~Post~ posts
  + toString() String
  + hashCode() int
  + equals(Object) boolean
   String password
   Role role
   LocalDateTime createdAt
   Long id
   Set~Topic~ topics
   String username
   Set~Post~ posts
}
class UserAlreadyExistsException {
  + UserAlreadyExistsException(String) 
}
class UserDetailsImpl {
  + UserDetailsImpl(User, Collection~GrantedAuthority~) 
  - String password
  - String username
  - Collection~GrantedAuthority~ authorities
   String password
   boolean enabled
   Collection~GrantedAuthority~ authorities
   String username
   boolean credentialsNonExpired
   boolean accountNonLocked
   boolean accountNonExpired
}
class UserDetailsServiceImpl {
  + UserDetailsServiceImpl() 
  + setUserRepository(UserRepository, RoleRepository) void
  + loadUserByUsername(String) UserDetails
}
class UserNotFoundException {
  + UserNotFoundException(String) 
  + UserNotFoundException(String, Throwable) 
}
class UserPayload {
  + UserPayload() 
  + UserPayload(String, String, LocalDateTime) 
  - String roleName
  - LocalDateTime createdAt
  - String username
  + equals(Object) boolean
  # canEqual(Object) boolean
  + hashCode() int
  + toString() String
   String roleName
   LocalDateTime createdAt
   String username
}
class UserRepository {
<<Interface>>
  + existsByUsername(String) boolean
  + getByUsername(String) User
  + findByUsername(String) Optional~User~
}
class UserService {
<<Interface>>
  + signIn(String, String) Optional~DecodedJWT~
  + signUp(String, String) User
}
class UserServiceImpl {
  + UserServiceImpl() 
  - RoleRepository roleRepository
  - PasswordEncoder passwordEncoder
  - JwtProvider jwtProvider
  - UserRepository userRepository
  + signUp(String, String) User
  - getByCredentials(String, String) User
  - findByCredentials(String, String) Optional~User~
  + signIn(String, String) Optional~DecodedJWT~
   RoleRepository roleRepository
   UserRepository userRepository
   JwtProvider jwtProvider
   PasswordEncoder passwordEncoder
}
class UserUpdatePayload {
  + UserUpdatePayload(String, String, String) 
  + UserUpdatePayload() 
  - String username
  - String password
  - String roleName
  + equals(Object) boolean
  # canEqual(Object) boolean
  + toString() String
  + hashCode() int
   String password
   String roleName
   String username
}
class WebSecurityConfig {
  + WebSecurityConfig() 
  - JwtFilter jwtFilter
  + filterChain(HttpSecurity) SecurityFilterChain
   PasswordEncoder encoder
   JwtFilter jwtFilter
}

AuthController "1" *--> "userService 1" UserService 
JwtFilter "1" *--> "jwtProvider 1" JwtProvider 
Post "1" *--> "topic 1" Topic 
Post "1" *--> "creator 1" User 
PostController "1" *--> "postService 1" PostService 
PostService  ..>  Post : «create»
PostService "1" *--> "postRepository 1" PostRepository 
PostService "1" *--> "topicRepository 1" TopicRepository 
PostService "1" *--> "userRepository 1" UserRepository 
Role "1" *--> "users *" User 
RoleController "1" *--> "roleService 1" RoleService 
RoleLoader  ..>  Role : «create»
RoleLoader "1" *--> "roleRepository 1" RoleRepository 
RoleService  ..>  Role : «create»
RoleService "1" *--> "roleRepository 1" RoleRepository 
Topic "1" *--> "posts *" Post 
Topic "1" *--> "creator 1" User 
TopicController "1" *--> "topicService 1" TopicService 
TopicService  ..>  Topic : «create»
TopicService "1" *--> "topicRepository 1" TopicRepository 
TopicService "1" *--> "userRepository 1" UserRepository 
User "1" *--> "posts *" Post 
User "1" *--> "role 1" Role 
User "1" *--> "topics *" Topic 
UserDetailsServiceImpl "1" *--> "roleRepository 1" RoleRepository 
UserDetailsServiceImpl  ..>  UserDetailsImpl : «create»
UserDetailsServiceImpl "1" *--> "userRepository 1" UserRepository 
UserServiceImpl "1" *--> "jwtProvider 1" JwtProvider 
UserServiceImpl  ..>  RoleNotFoundException : «create»
UserServiceImpl "1" *--> "roleRepository 1" RoleRepository 
UserServiceImpl  ..>  User : «create»
UserServiceImpl  ..>  UserAlreadyExistsException : «create»
UserServiceImpl  ..>  UserNotFoundException : «create»
UserServiceImpl "1" *--> "userRepository 1" UserRepository 
UserServiceImpl  ..>  UserService 
WebSecurityConfig "1" *--> "jwtFilter 1" JwtFilter 
